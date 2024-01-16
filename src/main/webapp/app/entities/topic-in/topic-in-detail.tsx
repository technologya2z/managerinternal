import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './topic-in.reducer';

export const TopicInDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const topicInEntity = useAppSelector(state => state.topicIn.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="topicInDetailsHeading">
          <Translate contentKey="cmspApp.topicIn.detail.title">TopicIn</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{topicInEntity.id}</dd>
          <dt>
            <span id="dateConnect">
              <span>ngày kết nối</span>
            </span>
          </dt>
          <dd>
            {topicInEntity.dateConnect ? <TextFormat value={topicInEntity.dateConnect} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="description">
             <span>Mô tả</span>
            </span>
          </dt>
          <dd>{topicInEntity.description}</dd>
          <dt>
           <span>Topic</span>
          </dt>
          <dd>{topicInEntity.topic ? topicInEntity.topic.name : ''}</dd>
          <dt>
            <Translate contentKey="cmspApp.topicIn.application">Application</Translate>
          </dt>
          <dd>{topicInEntity.application ? topicInEntity.application.name : ''}</dd>
        </dl>
        <Button tag={Link} to="/topic-in" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/topic-in/${topicInEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default TopicInDetail;
