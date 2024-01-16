import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './department.reducer';

export const DepartmentDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const departmentEntity = useAppSelector(state => state.department.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="departmentDetailsHeading">
          <Translate contentKey="cmspApp.department.detail.title">Department</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{departmentEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="cmspApp.department.name">Name</Translate>
            </span>
          </dt>
          <dd>{departmentEntity.name}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="cmspApp.department.code">Code</Translate>
            </span>
          </dt>
          <dd>{departmentEntity.code}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="cmspApp.department.description">Description</Translate>
            </span>
          </dt>
          <dd>{departmentEntity.description}</dd>
          <dt>
            <Translate contentKey="cmspApp.department.organization">Organization</Translate>
          </dt>
          <dd>{departmentEntity.organization ? departmentEntity.organization.name : ''}</dd>

          <dt>
            <span id="createdDate">Created Date</span>
          </dt>
          <dd>
            {departmentEntity.createdDate ? <TextFormat value={departmentEntity.createdDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="createdBy">Created By</span>
          </dt>
          <dd>{departmentEntity.createdBy}</dd>
          <dt>
            <span id="lastModifiedDate">Last Modified Date</span>
          </dt>
          <dd>
            {departmentEntity.lastModifiedDate ? (
              <TextFormat value={departmentEntity.lastModifiedDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="lastModifiedBy">Last Modified By</span>
          </dt>
          <dd>{departmentEntity.lastModifiedBy}</dd>
        </dl>
        <Button tag={Link} to="/department" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/department/${departmentEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DepartmentDetail;
