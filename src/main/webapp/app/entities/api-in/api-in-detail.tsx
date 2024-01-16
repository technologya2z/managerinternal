import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './api-in.reducer';

export const ApiInDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const apiInEntity = useAppSelector(state => state.apiIn.entity);
  return (
    <Row>
      <Col md="48">
        <h2 data-cy="apiInDetailsHeading">
          <Translate contentKey="cmspApp.apiIn.detail.title">ApiIn</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{apiInEntity.id}</dd>
          <dt>
            <span id="dateConnect">
              Ngày kết nối
            </span>
          </dt>
          <dd>
            {apiInEntity.dateConnect ? <TextFormat value={apiInEntity.dateConnect} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="description">
              <Translate contentKey="cmspApp.apiIn.description">Description</Translate>
            </span>
          </dt>
          <dd>{apiInEntity.description}</dd>
          <dt>
            <Translate contentKey="cmspApp.apiIn.apiInfo">Api Info</Translate>
          </dt>
          <dd>{apiInEntity.apiInfo ? apiInEntity.apiInfo.name : ''}</dd>
          <dt>
            <Translate contentKey="cmspApp.apiIn.application">Application</Translate>
          </dt>
          <dd>{apiInEntity.application ? apiInEntity.application.name : ''}</dd>

          <dt>
            <span id="createdDate">Ngày tạo</span>
          </dt>
          <dd>{apiInEntity.createdDate ? <TextFormat value={apiInEntity.createdDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="createdBy">Tạo bởi</span>
          </dt>
          <dd>{apiInEntity.createdBy}</dd>
          <dt>
            <span id="lastModifiedDate">Ngày sửa đổi</span>
          </dt>
          <dd>
            {apiInEntity.lastModifiedDate ? <TextFormat value={apiInEntity.lastModifiedDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="lastModifiedBy">sửa đổi bởi</span>
          </dt>
          <dd>{apiInEntity.lastModifiedBy}</dd>
        </dl>
        <Button tag={Link} to="/api-in" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/api-in/${apiInEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ApiInDetail;
