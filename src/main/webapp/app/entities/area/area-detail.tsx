import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { getEntity } from './area.reducer';

export const AreaDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const areaEntity = useAppSelector(state => state.area.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="areaDetailsHeading">
          <Translate contentKey="cmspApp.area.detail.title">Area</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{areaEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="cmspApp.area.name">Name</Translate>
            </span>
          </dt>
          <dd>{areaEntity.name}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="cmspApp.area.code">Code</Translate>
            </span>
          </dt>
          <dd>{areaEntity.code}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="cmspApp.area.description">Description</Translate>
            </span>
          </dt>
          <dd>{areaEntity.description}</dd>

          <dt>
            <span id="createdDate">Created Date</span>
          </dt>
          <dd>{areaEntity.createdDate ? <TextFormat value={areaEntity.createdDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="createdBy">Created By</span>
          </dt>
          <dd>{areaEntity.createdBy}</dd>
          <dt>
            <span id="lastModifiedDate">Last Modified Date</span>
          </dt>
          <dd>
            {areaEntity.lastModifiedDate ? <TextFormat value={areaEntity.lastModifiedDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="lastModifiedBy">Last Modified By</span>
          </dt>
          <dd>{areaEntity.lastModifiedBy}</dd>
        </dl>
        <Button tag={Link} to="/area" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/area/${areaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AreaDetail;
