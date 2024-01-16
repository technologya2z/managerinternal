import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './topic-out.reducer';

export const TopicOutDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const topicOutEntity = useAppSelector(state => state.topicOut.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="topicOutDetailsHeading">
          <Translate contentKey="cmspApp.topicOut.detail.title">TopicOut</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{topicOutEntity.id}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="cmspApp.topicOut.description">Description</Translate>
            </span>
          </dt>
          <dd>{topicOutEntity.description}</dd>
          <dt>
            <span id="dateConnect">
              <Translate contentKey="cmspApp.topicOut.dateConnect">Date Connect</Translate>
            </span>
          </dt>
          <dd>
            {topicOutEntity.dateConnect ? (
              <TextFormat value={topicOutEntity.dateConnect} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="cmspApp.topicOut.topic">Topic</Translate>
          </dt>
          <dd>{topicOutEntity.topic ? topicOutEntity.topic.name : ''}</dd>
          <dt>
            <Translate contentKey="cmspApp.topicOut.application">Application</Translate>
          </dt>
          <dd>{topicOutEntity.application ? topicOutEntity.application.name : ''}</dd>
        </dl>
        <Button tag={Link} to="/topic-out" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/topic-out/${topicOutEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default TopicOutDetail;
