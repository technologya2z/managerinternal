import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState, JhiPagination, JhiItemCount, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDatabaseInfo } from 'app/shared/model/database-info.model';
import { getEntities, searchEntities } from './database-info.reducer';

export const DatabaseInfo = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  const databaseInfoList = useAppSelector(state => state.databaseInfo.entities);
  const loading = useAppSelector(state => state.databaseInfo.loading);
  const totalItems = useAppSelector(state => state.databaseInfo.totalItems);

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
    if (values['serviceName']) {
      endURL += `&serviceName=${values['serviceName']}`;
      searchCriterials['serviceName.contains'] = values['serviceName'];
    }

    dispatch(
      searchEntities({
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
      <h2 id="database-info-heading" data-cy="DatabaseInfoHeading">
        <span>Thông tin database</span>
        <div className="d-flex justify-content-end">
          <ValidatedForm defaultValues={{}} onSubmit={handleFilter}>
            <ValidatedField
              label=""
              id="database-name"
              name="name"
              data-cy="name"
              type="text"
              placeHolder="Tên Database"
              className="float-start me-2 mt-1"
            />
            <ValidatedField
              label=""
              id="database-serviceName"
              name="serviceName"
              data-cy="serviceName"
              type="text"
              placeHolder="service Name"
              className="float-start me-2 mt-1"
            />

            <Button className="me-2" color="success" type="submit" disabled={loading}>
              <FontAwesomeIcon icon="sync" spin={loading} /> search
            </Button>
            <Link to="/database-info/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
              <FontAwesomeIcon icon="plus" />
              &nbsp;
             <span>Tạo mới Database</span>
            </Link>
          </ValidatedForm>
        </div>
      </h2>
      <div className="table-responsive">
        {databaseInfoList && databaseInfoList.length > 0 ? (
          <Table responsive bordered>
            <thead>
              <tr>
                <th>
                  <label>STT</label>
                </th>
                <th className="hand" onClick={sort('name')}>
                  <Translate contentKey="cmspApp.databaseInfo.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('serviceName')}>
                  <Translate contentKey="cmspApp.databaseInfo.serviceName">Service Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('userName')}>
                  <Translate contentKey="cmspApp.databaseInfo.userName">User Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('ipServer')}>
                  <Translate contentKey="cmspApp.databaseInfo.ipServer">Ip Server</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('port')}>
                  <Translate contentKey="cmspApp.databaseInfo.port">Port</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('dataType')}>
                  <Translate contentKey="cmspApp.databaseInfo.dataType">Data Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {databaseInfoList.map((databaseInfo, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>{i + 1}</td>
                  <td>{databaseInfo.name}</td>
                  <td>{databaseInfo.serviceName}</td>
                  <td>{databaseInfo.userName}</td>
                  <td>{databaseInfo.ipServer}</td>
                  <td>{databaseInfo.port}</td>
                  <td>
                    <Translate contentKey={`cmspApp.DatabaseType.${databaseInfo.dataType}`} />
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/database-info/${databaseInfo.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/database-info/${databaseInfo.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`/database-info/${databaseInfo.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="cmspApp.databaseInfo.home.notFound">No Database Infos found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={databaseInfoList && databaseInfoList.length > 0 ? '' : 'd-none'}>
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

export default DatabaseInfo;
