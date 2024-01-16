import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './access-log.reducer';

export const AccessLogDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const accessLogEntity = useAppSelector(state => state.accessLog.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="accessLogDetailsHeading">AccessLog</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{accessLogEntity.id}</dd>
          <dt>
            <span id="empCode">Emp Code</span>
          </dt>
          <dd>{accessLogEntity.empCode}</dd>
          <dt>
            <span id="empUsername">Emp Username</span>
          </dt>
          <dd>{accessLogEntity.empUsername}</dd>
          <dt>
            <span id="empFullName">Emp Full Name</span>
          </dt>
          <dd>{accessLogEntity.empFullName}</dd>
          <dt>
            <span id="accessResource">Access Resource</span>
          </dt>
          <dd>{accessLogEntity.accessResource}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{accessLogEntity.description}</dd>
          <dt>
            <span id="ipAddress">Ip Address</span>
          </dt>
          <dd>{accessLogEntity.ipAddress}</dd>
          <dt>
            <span id="accessTime">Access Time</span>
          </dt>
          <dd>
            {accessLogEntity.accessTime ? <TextFormat value={accessLogEntity.accessTime} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
        </dl>
        <Button tag={Link} to="/access-log" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/access-log/${accessLogEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default AccessLogDetail;
