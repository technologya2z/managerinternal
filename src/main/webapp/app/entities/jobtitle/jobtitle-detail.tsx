import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './jobtitle.reducer';

export const JobtitleDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const jobtitleEntity = useAppSelector(state => state.jobtitle.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="jobtitleDetailsHeading">
          <Translate contentKey="cmspApp.jobtitle.detail.title">Jobtitle</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{jobtitleEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="cmspApp.jobtitle.name">Name</Translate>
            </span>
          </dt>
          <dd>{jobtitleEntity.name}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="cmspApp.jobtitle.code">Code</Translate>
            </span>
          </dt>
          <dd>{jobtitleEntity.code}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="cmspApp.jobtitle.description">Description</Translate>
            </span>
          </dt>
          <dd>{jobtitleEntity.description}</dd>

          <dt>
            <span id="createdDate">Created Date</span>
          </dt>
          <dd>
            {jobtitleEntity.createdDate ? <TextFormat value={jobtitleEntity.createdDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="createdBy">Created By</span>
          </dt>
          <dd>{jobtitleEntity.createdBy}</dd>
          <dt>
            <span id="lastModifiedDate">Last Modified Date</span>
          </dt>
          <dd>
            {jobtitleEntity.lastModifiedDate ? (
              <TextFormat value={jobtitleEntity.lastModifiedDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="lastModifiedBy">Last Modified By</span>
          </dt>
          <dd>{jobtitleEntity.lastModifiedBy}</dd>
        </dl>
        <Button tag={Link} to="/jobtitle" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/jobtitle/${jobtitleEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default JobtitleDetail;
