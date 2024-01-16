import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, { useEffect, useState } from 'react';
import { JhiItemCount, JhiPagination, TextFormat, Translate, getSortState } from 'react-jhipster';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';

import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';

import { ValidatedField, ValidatedForm } from 'react-jhipster';

import { ApplicationType } from 'app/shared/model/enumerations/application-type.model';
import { getEntities, searchEntitiesApplication } from './application.reducer';

export const Application = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  const applicationList = useAppSelector(state => state.application.entities);
  const loading = useAppSelector(state => state.application.loading);
  const totalItems = useAppSelector(state => state.application.totalItems);
  const applicationTypeValues = Object.keys(ApplicationType);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      })
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (props.location.search !== endURL) {
      props.history.push(`${props.location.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(props.location.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [props.location.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  const handleFilter = values => {
    let endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    const searchCriterials = [];
    if (values['name']) {
      endURL += `&name=${values['name']}`;
      searchCriterials['name.contains'] = values['name'];
    }
    dispatch(
      searchEntitiesApplication({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
        searchCriterials,
      })
    );

    if (props.location.search !== endURL) {
      props.history.push(`${props.location.pathname}${endURL}`);
    }
  };

  const { match } = props;

  return (
    <div>
      <h2 id="application-heading" data-cy="ApplicationHeading">
        <Translate contentKey="cmspApp.application.home.title">Applications</Translate>
        <div className="d-flex justify-content-end">
          <ValidatedForm defaultValues={{}} onSubmit={handleFilter}>
            <ValidatedField
              label=""
              id="application-name"
              name="name"
              data-cy="name"
              type="text"
              placeHolder="tên ứng dụng"
              className="float-start me-2 mt-1"
            />

            <Button className="me-2" color="success" type="submit" disabled={loading}>
              <FontAwesomeIcon icon="sync" spin={loading} /> Tìm kiếm
            </Button>
            <Link to="/application/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
              <FontAwesomeIcon icon="plus" />
              &nbsp;
              Tạo mới
            </Link>
          </ValidatedForm>
        </div>
      </h2>

      <div className="table-responsive">
        {applicationList && applicationList.length > 0 ? (
          <Table responsive bordered>
            <thead>
              <tr>
                {/* <th className="hand" onClick={sort('STT')}>
                  <label>STT</label>
                </th> */}
                <th className="hand" onClick={sort('name')}>
                  <Translate contentKey="cmspApp.application.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>

                <th className="hand" onClick={sort('enviroment')}>
                  <Translate contentKey="cmspApp.application.enviroment">Enviroment</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('subComponent')}>
                  <Translate contentKey="cmspApp.application.subComponent">Sub Component</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('appType')}>
                  <Translate contentKey="cmspApp.application.appType">App Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th onClick={sort('humans')}>
                  <Translate contentKey="cmspApp.application.humans">Humans</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {applicationList.map((application, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  {/* <td>{i + 1}</td> */}
                  <td>{application.name}</td>
                  <td>{application.enviroment}</td>
                  <td>{application.subComponent ? JSON.parse(application.subComponent).map((obj) => obj.name+',')  : null}</td>
                  <td>
                    <label>{application.appType==='PARTNER'? 'Đối tác':'Nội bộ' }</label>
                  </td>
                  <td>
                    {application.humans ? (
                      <Link to={`/humans/${application.humans.id}`}>
                        {application.humans.fullName}
                        {application.humans.map(otherEntity => otherEntity.fullName + ',')}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/application/${application.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/application/${application.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/application/${application.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="cmspApp.application.home.notFound">No Applications found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={applicationList && applicationList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default Application;
