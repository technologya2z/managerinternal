import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState, JhiPagination, JhiItemCount, ValidatedForm, ValidatedField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { MultiSelect } from 'primereact/multiselect';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { getEntities, searchEntitiesApiIn } from './api-in.reducer';
import Axios from "axios";

export const ApiIn = (props: RouteComponentProps<{ url: string, id: string }>) => {
  const dispatch = useAppDispatch();
  const [valueApplication, setvalueApplication] = useState([]);
  const [valueApi, setvalueApi] = useState([]);
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );
  const [multipleApplications, setmultipleApplications] = useState([]);
  const [multipleAPI, setmultipleAPI] = useState([]);

  const apiInList = useAppSelector(state => state.apiIn.entities);
  const loading = useAppSelector(state => state.apiIn.loading);
  const totalItems = useAppSelector(state => state.apiIn.totalItems);

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
    getAllEntities();
    const requestUrlApplication = `api/applications?page=0&size=5000&sort=id,asc`;
    Axios.get(`${requestUrlApplication}`).then(res => {
      const data = res.data;
      setmultipleApplications(data);
    });

    const requestUrlApiInfo = `api/api-infos?page=0&size=5000&sort=id,asc`;
    Axios.get(`${requestUrlApiInfo}`).then(res => {
      const data = res.data;
      setmultipleAPI(data);
    });

  }, []);


  useEffect(() => {
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

  const handleFilter = values => {
    let endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    const searchCriterials = [];
    if (values['description']) {
      endURL += `&description=${values['description']}`;
      searchCriterials['description.contains'] = values['description'];
    }
    if (valueApplication && valueApplication.length > 0) {
      const appIDS = valueApplication.map((obj) => obj.id);
      searchCriterials['applicationId.in'] = appIDS;
    }

    if (valueApi && valueApi.length > 0) {
      const apiIDS = valueApi.map((obj) => obj.id);
      searchCriterials['apiInfoId.in'] = apiIDS;
    }


    dispatch(
      searchEntitiesApiIn({
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

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const { match } = props;

  return (
    <div>
      <h2 id="api-in-heading" data-cy="ApiInHeading">
        <p>Ứng dụng gọi đến API</p>
        <div className="d-flex justify-content-end">
        <ValidatedForm defaultValues={{}} onSubmit={handleFilter}>
            <MultiSelect
                  style={{minWidth:'200px'}}
                  value={valueApplication}
                  onChange={e => setvalueApplication(e.value)}
                  options={multipleApplications}
                  optionLabel="name"
                  filter
                  showClear
                  filterBy="name"
                  placeholder="Ứng dụng"
                  maxSelectedLabels={1}
                />
                 &nbsp;

          <MultiSelect
            style={{minWidth:'200px',marginRight:'8px'}}
            value={valueApi}
            onChange={e => setvalueApi(e.value)}
            options={multipleAPI}
            optionLabel="name"
            filter
            showClear
            filterBy="name"
            placeholder="API"
            maxSelectedLabels={1}
          />

            <Button className="me-2" color="success" type="submit" disabled={loading}>
              <FontAwesomeIcon icon="search" spin={loading} /> Tìm kiếm
            </Button>
          <Link to="/api-in/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
           Tạo mới
          </Link>
          </ValidatedForm>
        </div>
      </h2>
      <div className="table-responsive">
        {apiInList && apiInList.length > 0 ? (
          <Table responsive bordered>
            <thead>
              <tr>
                <th>
                  <label>STT</label>
                </th>
                <th>
                  <Translate contentKey="cmspApp.apiIn.application">Application</Translate>
                </th>
                <th>
                  API call đến
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="cmspApp.apiIn.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('dateConnect')}>
                  <Translate contentKey="cmspApp.apiIn.dateConnect">Date Connect</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {apiInList.map((apiIn, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>{i + 1}</td>
                  <td>{apiIn.application ? <Link to={`/application/${apiIn.application.id}`}>{apiIn.application.name}</Link> : ''}</td>
                  <td>{apiIn.apiInfo ? <Link to={`/api-info/${apiIn.apiInfo.id}`}>{apiIn.apiInfo.name}</Link> : ''}</td>
                  <td>{apiIn.description}</td>
                  <td>{apiIn.dateConnect ? <TextFormat type="date" value={apiIn.dateConnect} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/api-in/${apiIn.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/api-in/${apiIn.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`/api-in/${apiIn.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="cmspApp.apiIn.home.notFound">No Api Ins found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={apiInList && apiInList.length > 0 ? '' : 'd-none'}>
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

export default ApiIn;
