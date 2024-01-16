import React, { useEffect, useState } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Table } from 'reactstrap';
import { getSortState, TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './topic.reducer';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { searchEntitiesApplication } from 'app/entities/application/application.reducer';
import { searchEntitiesApiIn } from 'app/entities/api-in/api-in.reducer';
import { searchEntitiesTopicIn } from 'app/entities/topic-in/topic-in.reducer';
import {searchEntitiesTopicOut} from 'app/entities/topic-out/topic-out.reducer';

export const TopicDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
    handleFilterTopicIn();
    handleFilterTopicOut();
  }, []);

  const topicEntity = useAppSelector(state => state.topic.entity);

  const topicInEntity = useAppSelector(state => state.topicIn.entities);
  const topicOutEntity = useAppSelector(state => state.topicOut.entities);
  const applicationList = topicEntity.applications;

  const [paginationStateApplication, setPaginationStateApplication] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  const handleFilterApplication = values => {
    const searchCriterials = [];
    if (values['applications']) {
      searchCriterials['applications.equals'] = values['applications'];
    }

    dispatch(
      searchEntitiesApplication({
        page: paginationStateApplication.activePage - 1,
        size: paginationStateApplication.itemsPerPage,
        sort: `${paginationStateApplication.sort},${paginationStateApplication.order}`,
        searchCriterials,
      })
    );
  };

  const handleFilterTopicIn = () => {
    const searchCriterials = [];
    searchCriterials['topicId.equals'] = props.match.params.id;
    dispatch(
      searchEntitiesTopicIn({
        page: paginationStateApplication.activePage - 1,
        size: paginationStateApplication.itemsPerPage,
        sort: `${paginationStateApplication.sort},${paginationStateApplication.order}`,
        searchCriterials,
      })
    );
  };

  const handleFilterTopicOut = () => {
    const searchCriterials = [];
    searchCriterials['topicId.equals'] = props.match.params.id;
    dispatch(
      searchEntitiesTopicOut({
        page: paginationStateApplication.activePage - 1,
        size: paginationStateApplication.itemsPerPage,
        sort: `${paginationStateApplication.sort},${paginationStateApplication.order}`,
        searchCriterials,
      })
    );
  };

  return (
    <div>
      <Row>
        <Col md="8">
          <h2 data-cy="topicDetailsHeading">
            <Translate contentKey="cmspApp.topic.detail.title">Topic</Translate>
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="id">
                <Translate contentKey="global.field.id">ID</Translate>
              </span>
            </dt>
            <dd>{topicEntity.id}</dd>
            <dt>
              <span id="name">
                <Translate contentKey="cmspApp.topic.name">Name</Translate>
              </span>
            </dt>
            <dd>{topicEntity.name}</dd>
            <dt>
              <span id="bootrapServer">
                <Translate contentKey="cmspApp.topic.bootrapServer">Bootrap Server</Translate>
              </span>
            </dt>
            <dd>{topicEntity.bootrapServer}</dd>
            <dt>
              <span id="locationTopic">
                <Translate contentKey="cmspApp.topic.locationTopic">Location Topic</Translate>
              </span>
            </dt>
            <dd>{topicEntity.locationTopic}</dd>
            <dt>
              <span id="rootproducer">
                <Translate contentKey="cmspApp.topic.rootproducer">Rootproducer</Translate>
              </span>
            </dt>
            <dd>{topicEntity.rootproducer}</dd>
            <dt>
              <span id="rootTable">
                <Translate contentKey="cmspApp.topic.rootTable">Root Table</Translate>
              </span>
            </dt>
            <dd>{topicEntity.rootTable}</dd>

            <dt>
              <Translate contentKey="cmspApp.topic.application">Application</Translate>
            </dt>
            <dd>
              {topicEntity.applications
                ? topicEntity.applications.map((val, i) => (
                    <span key={val.id}>
                      <a href={'application/' + val.id}>{val.name}</a>
                      {topicEntity.applications && i === topicEntity.applications.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>

            <dt>
              <span id="message">
                <Translate contentKey="cmspApp.topic.message">Message</Translate>
              </span>
            </dt>
            <dd>{topicEntity.message}</dd>
            <dt>
              <span id="linkDoc">
                <Translate contentKey="cmspApp.topic.linkDoc">Link Doc</Translate>
              </span>
            </dt>
            <dd>{topicEntity.linkDoc}</dd>
          </dl>
          <Button tag={Link} to="/topic" replace color="info" data-cy="entityDetailsBackButton">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/topic/${topicEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>

      <Row>
        <h4 id="topic-in-heading" data-cy="TopicInHeading">
          <Translate contentKey="cmspApp.topicIn.home.title">Topic Ins</Translate>
        </h4>
        <div className="table-responsive">
          {topicInEntity && topicInEntity.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th>
                    <label>STT</label>
                  </th>
                  <th>
                    <Translate contentKey="cmspApp.topicIn.topic">Topic</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="cmspApp.topicIn.application">Application</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand">
                    <Translate contentKey="cmspApp.topicIn.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand">
                    <Translate contentKey="cmspApp.topicIn.dateConnect">Date Connect</Translate> <FontAwesomeIcon icon="sort" />
                  </th>

                  <th />
                </tr>
              </thead>
              <tbody>
                {topicInEntity.map((topicIn, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>{i + 1}</td>
                    <td>{topicIn.topic ? <Link to={`/topic/${topicIn.topic.id}`}>{topicIn.topic.name}</Link> : ''}</td>
                    <td>
                      {topicIn.application ? <Link to={`/application/${topicIn.application.id}`}>{topicIn.application.name}</Link> : ''}
                    </td>
                    <td>{topicIn.description}</td>
                    <td>
                      {topicIn.dateConnect ? <TextFormat type="date" value={topicIn.dateConnect} format={APP_LOCAL_DATE_FORMAT} /> : null}
                    </td>
                    <td className="text-end">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`/topic-in/${topicIn.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">
              <Translate contentKey="cmspApp.topicIn.home.notFound">No Topic Ins found</Translate>
            </div>
          )}
        </div>
      </Row>

      <Row>
        <h4 id="topic-in-heading" data-cy="TopicInHeading">
          Producer
        </h4>
        <div className="table-responsive">
          {topicOutEntity && topicOutEntity.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th>
                    <label>STT</label>
                  </th>
                  <th>
                    <Translate contentKey="cmspApp.topicIn.topic">Topic</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="cmspApp.topicIn.application">Application</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand">
                    <Translate contentKey="cmspApp.topicIn.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand">
                    <Translate contentKey="cmspApp.topicIn.dateConnect">Date Connect</Translate> <FontAwesomeIcon icon="sort" />
                  </th>

                  <th />
                </tr>
              </thead>
              <tbody>
                {topicOutEntity.map((topicIn, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>{i + 1}</td>
                    <td>{topicIn.topic ? <Link to={`/topic/${topicIn.topic.id}`}>{topicIn.topic.name}</Link> : ''}</td>
                    <td>
                      {topicIn.application ? <Link to={`/application/${topicIn.application.id}`}>{topicIn.application.name}</Link> : ''}
                    </td>
                    <td>{topicIn.description}</td>
                    <td>
                      {topicIn.dateConnect ? <TextFormat type="date" value={topicIn.dateConnect} format={APP_LOCAL_DATE_FORMAT} /> : null}
                    </td>
                    <td className="text-end">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`/topic-in/${topicIn.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">
              <Translate contentKey="cmspApp.topicIn.home.notFound">No Topic Ins found</Translate>
            </div>
          )}
        </div>
      </Row>
    </div>
  );
};

export default TopicDetail;
