import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { ValidatedField, ValidatedForm } from 'react-jhipster';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ITopicOut } from 'app/shared/model/topic-out.model';
import { getEntities, searchEntitiesTopicOut } from './topic-out.reducer';

export const TopicOut = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  const topicOutList = useAppSelector(state => state.topicOut.entities);
  const loading = useAppSelector(state => state.topicOut.loading);
  const totalItems = useAppSelector(state => state.topicOut.totalItems);

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
  const handleFilter = values => {
    let endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    const searchCriterials = [];
    if (values['description']) {
      endURL += `&description=${values['description']}`;
      searchCriterials['description.contains'] = values['description'];
    }
    dispatch(
      searchEntitiesTopicOut({
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

  const { match } = props;

  return (
    <div>
      <h2 id="topic-out-heading" data-cy="TopicOutHeading">
        <Translate contentKey="cmspApp.topicOut.home.title">Topic Outs</Translate>
        <div className="d-flex justify-content-end">
        <ValidatedForm defaultValues={{}} onSubmit={handleFilter}>
        <ValidatedField
              label=""
              id="topicOut-description"
              name="description"
              data-cy="description"
              type="text"
              placeHolder="Mô tả  "
              className="float-start me-2 mt-1"
            />
            <Button className="me-2" color="success" type="submit" disabled={loading}>
              <FontAwesomeIcon icon="search" spin={loading} /> Tìm kiếm
            </Button>
          <Link to="/topic-out/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="cmspApp.topicOut.home.createLabel">Create new Topic Out</Translate>
          </Link>
        </ValidatedForm>
        </div>
      </h2>
      <div className="table-responsive">
        {topicOutList && topicOutList.length > 0 ? (
          <Table responsive bordered>
            <thead>
              <tr>
                <th>
                  <label>STT</label>
                </th>
                <th>
                  <Translate contentKey="cmspApp.topicOut.topic">Topic</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="cmspApp.topicOut.application">Application</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="cmspApp.topicOut.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('dateConnect')}>
                  <Translate contentKey="cmspApp.topicOut.dateConnect">Date Connect</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {topicOutList.map((topicOut, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>{i + 1}</td>
                  <td>{topicOut.topic ? <Link to={`/topic/${topicOut.topic.id}`}>{topicOut.topic.name}</Link> : ''}</td>
                  <td>
                    {topicOut.application ? <Link to={`/application/${topicOut.application.id}`}>{topicOut.application.name}</Link> : ''}
                  </td>
                  <td>{topicOut.description}</td>
                  <td>
                    {topicOut.dateConnect ? <TextFormat type="date" value={topicOut.dateConnect} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/topic-out/${topicOut.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/topic-out/${topicOut.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`/topic-out/${topicOut.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="cmspApp.topicOut.home.notFound">No Topic Outs found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={topicOutList && topicOutList.length > 0 ? '' : 'd-none'}>
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

export default TopicOut;
