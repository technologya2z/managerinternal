import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState, JhiPagination, JhiItemCount, ValidatedForm, ValidatedField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ITopic } from 'app/shared/model/topic.model';
import { getEntities, searchEntitiesTopic } from './topic.reducer';

export const Topic = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  const topicList = useAppSelector(state => state.topic.entities);
  const loading = useAppSelector(state => state.topic.loading);
  const totalItems = useAppSelector(state => state.topic.totalItems);

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

  const handleFilter = values => {
    let endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    const searchCriterials = [];
    if (values['name']) {
      endURL += `&name=${values['name']}`;
      searchCriterials['name.contains'] = values['name'];
    }
    if (values['locationTopic']) {
      endURL += `&locationTopic=${values['locationTopic']}`;
      searchCriterials['locationTopic.contains'] = values['locationTopic'];
    }
    if (values['rootTable']) {
      endURL += `&rootTable=${values['rootTable']}`;
      searchCriterials['rootTable.contains'] = values['rootTable'];
    }
    if (values['rootproducer']) {
      endURL += `&rootproducer=${values['rootproducer']}`;
      searchCriterials['rootproducer.contains'] = values['rootproducer'];
    }

    dispatch(
      searchEntitiesTopic({
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

  const { match } = props;

  return (
    <div>
      <h2 id="topic-heading" data-cy="TopicHeading">
        <Translate contentKey="cmspApp.topic.home.title">Topics</Translate>
        <div className="d-flex justify-content-end">
          <ValidatedForm defaultValues={{}} onSubmit={handleFilter}>
            <ValidatedField
              label=""
              id="topic-name"
              name="name"
              data-cy="name"
              type="text"
              placeHolder="Tên topic"
              className="float-start me-2 mt-1"
            />
            <ValidatedField
              label=""
              id="topic-locationTopic"
              name="locationTopic"
              data-cy="locationTopic"
              type="text"
              placeHolder="Nơi đặt topic"
              className="float-start me-2 mt-1"
            />

            <ValidatedField
              label=""
              id="topic-rootTable"
              name="rootTable"
              data-cy="rootTable"
              type="text"
              placeHolder="Bảng nguồn"
              className="float-start me-2 mt-1"
            />

            <ValidatedField
              label=""
              id="topic-rootproducer"
              name="rootproducer"
              data-cy="rootproducer"
              type="text"
              placeHolder="Hệ thống nguồn"
              className="float-start me-2 mt-1"
            />

            <Button className="me-2" color="success" type="submit" disabled={loading}>
              <FontAwesomeIcon icon="search" spin={loading} /> search
            </Button>
            <Link to="/topic/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
              <FontAwesomeIcon icon="plus" />
              &nbsp;
             <span>tạo mới topic</span>
            </Link>
          </ValidatedForm>
        </div>
      </h2>
      <div className="table-responsive">
        {topicList && topicList.length > 0 ? (
          <Table responsive bordered>
            <thead>
              <tr>
                <th>
                  <label>STT</label>
                </th>
                <th className="hand" onClick={sort('name')}>
                  <Translate contentKey="cmspApp.topic.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('enviroment')}>
                  <Translate contentKey="cmspApp.topic.enviroment">enviroment</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('bootrapServer')}>
                  <Translate contentKey="cmspApp.topic.bootrapServer">Bootrap Server</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('locationTopic')}>
                  <Translate contentKey="cmspApp.topic.locationTopic">Location Topic</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('rootproducer')}>
                  <Translate contentKey="cmspApp.topic.rootproducer">Rootproducer</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('rootTable')}>
                  <Translate contentKey="cmspApp.topic.rootTable">Root Table</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('message')}>
                  <Translate contentKey="cmspApp.topic.message">Message</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('linkDoc')}>
                  <Translate contentKey="cmspApp.topic.linkDoc">Link Doc</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {topicList.map((topic, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>{i + 1}</td>
                  <td>{topic.name}</td>
                  <td>{topic.enviroment}</td>
                  <td>{topic.bootrapServer}</td>
                  <td>{topic.locationTopic}</td>
                  <td>{topic.rootproducer}</td>
                  <td>{topic.rootTable}</td>
                  <td>{topic.message}</td>
                  <td>{topic.linkDoc}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/topic/${topic.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/topic/${topic.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`/topic/${topic.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="cmspApp.topic.home.notFound">No Topics found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={topicList && topicList.length > 0 ? '' : 'd-none'}>
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

export default Topic;
