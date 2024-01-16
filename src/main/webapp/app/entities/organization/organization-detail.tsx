import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './organization.reducer';

export const OrganizationDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const organizationEntity = useAppSelector(state => state.organization.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="organizationDetailsHeading">
          <Translate contentKey="cmspApp.organization.detail.title">Organization</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{organizationEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="cmspApp.organization.name">Name</Translate>
            </span>
          </dt>
          <dd>{organizationEntity.name}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="cmspApp.organization.code">Code</Translate>
            </span>
          </dt>
          <dd>{organizationEntity.code}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="cmspApp.organization.description">Description</Translate>
            </span>
          </dt>
          <dd>{organizationEntity.description}</dd>
          <dt>
            <span id="address">
              <Translate contentKey="cmspApp.organization.address">Address</Translate>
            </span>
          </dt>
          <dd>{organizationEntity.address}</dd>
          <dt>
            <span id="phoneNumber">
              <Translate contentKey="cmspApp.organization.phoneNumber">Phone Number</Translate>
            </span>
          </dt>
          <dd>{organizationEntity.phoneNumber}</dd>
          <dt>
            <Translate contentKey="cmspApp.organization.area">Area</Translate>
          </dt>
          <dd>{organizationEntity.area ? organizationEntity.area.name : ''}</dd>

          <dt>
            <span id="createdDate">Created Date</span>
          </dt>
          <dd>
            {organizationEntity.createdDate ? (
              <TextFormat value={organizationEntity.createdDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">Created By</span>
          </dt>
          <dd>{organizationEntity.createdBy}</dd>
          <dt>
            <span id="lastModifiedDate">Last Modified Date</span>
          </dt>
          <dd>
            {organizationEntity.lastModifiedDate ? (
              <TextFormat value={organizationEntity.lastModifiedDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="lastModifiedBy">Last Modified By</span>
          </dt>
          <dd>{organizationEntity.lastModifiedBy}</dd>
        </dl>
        <Button tag={Link} to="/organization" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/organization/${organizationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default OrganizationDetail;
