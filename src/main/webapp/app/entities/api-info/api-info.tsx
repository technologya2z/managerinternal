import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState, JhiPagination, JhiItemCount, ValidatedForm, ValidatedField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import {getEntities, searchEntitiesApiInfo} from './api-info.reducer';

export const ApiInfo = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  const apiInfoList = useAppSelector(state => state.apiInfo.entities);
  const loading = useAppSelector(state => state.apiInfo.loading);
  const totalItems = useAppSelector(state => state.apiInfo.totalItems);

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
    handleFilter([]);
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

  const handleFilter = values => {
    let endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    const searchCriterials = [];
    if (values['name']) {
      endURL += `&name=${values['name']}`;
      searchCriterials['name.contains'] = values['name'];
    }
    if (values['application']) {
      endURL += `&application=${values['application']}`;
      searchCriterials['application.contains'] = values['application'];
    }
    if (values['path']) {
      endURL += `&path=${values['path']}`;
      searchCriterials['path.contains'] = values['path'];
    }
    dispatch(
      searchEntitiesApiInfo({
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
      <h2 id="api-info-heading" data-cy="ApiInfoHeading">
        <Translate contentKey="cmspApp.apiInfo.home.title">Api Infos</Translate>
        <div className="d-flex justify-content-end">
          <ValidatedForm defaultValues={{}} onSubmit={handleFilter}>
            <ValidatedField
              label=""
              id="apiInfo-name"
              name="name"
              data-cy="name"
              type="text"
              placeHolder="Tên api"
              className="float-start me-2 mt-1"
            />
            <ValidatedField
              label=""
              id="apiInfo-path"
              name="path"
              data-cy="path"
              type="text"
              placeHolder="path endpoint"
              className="float-start me-2 mt-1"
            />
            <Button className="me-2" color="success" type="submit" disabled={loading}>
              <FontAwesomeIcon icon="sync" spin={loading} /> Tìm kiếm
            </Button>
            <Link to="/api-info/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
              <FontAwesomeIcon icon="plus" />
              &nbsp;
              <Translate contentKey="cmspApp.apiInfo.home.createLabel">Create new Api Info</Translate>
            </Link>
          </ValidatedForm>
        </div>
      </h2>
      <div className="table-responsive">
        {apiInfoList && apiInfoList.length > 0 ? (
          <Table responsive bordered>
            <thead>
              <tr>
                <th>
                  <label>STT</label>
                </th>
                <th className="hand" onClick={sort('name')}>
                  <Translate contentKey="cmspApp.apiInfo.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('method')}>
                  <Translate contentKey="cmspApp.apiInfo.method">method</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('application')}>
                  <Translate contentKey="cmspApp.apiInfo.application">Application</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('path')}>
                  <Translate contentKey="cmspApp.apiInfo.path">Path</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="cmspApp.apiInfo.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {apiInfoList.map((apiInfo, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>{i + 1}</td>
                  <td>{apiInfo.name}</td>
                  <td>{apiInfo.method}</td>
                  <td>
                    {apiInfo.application ? <Link to={`/application/${apiInfo.application.id}`}>{apiInfo.application.name}</Link> : ''}
                  </td>
                  <td>{apiInfo.path}</td>
                  <td>{apiInfo.description}</td>

                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/api-info/${apiInfo.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/api-info/${apiInfo.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`/api-info/${apiInfo.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="cmspApp.apiInfo.home.notFound">No Api Infos found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={apiInfoList && apiInfoList.length > 0 ? '' : 'd-none'}>
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

export default ApiInfo;
