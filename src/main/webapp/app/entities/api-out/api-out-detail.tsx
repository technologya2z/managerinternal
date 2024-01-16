import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './api-out.reducer';

export const ApiOutDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const apiOutEntity = useAppSelector(state => state.apiOut.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="apiOutDetailsHeading">
          <Translate contentKey="cmspApp.apiOut.detail.title">ApiOut</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
             ID
            </span>
          </dt>
          <dd>{apiOutEntity.id}</dd>
          <dt>
            <span id="description">
             Mô tả
            </span>
          </dt>
          <dd>{apiOutEntity.description}</dd>
          <dt>
            <span id="dateConnect">
             ngày kết nối
            </span>
          </dt>
          <dd>
            {apiOutEntity.dateConnect ? <TextFormat value={apiOutEntity.dateConnect} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <Translate contentKey="cmspApp.apiOut.apiInfo">Api Info</Translate>
          </dt>
          <dd>{apiOutEntity.apiInfo ? apiOutEntity.apiInfo.name : ''}</dd>
          <dt>
           <span>Ứng dụng</span>
          </dt>
          <dd>{apiOutEntity.application ? apiOutEntity.application.name : ''}</dd>

          <dt>
            <span id="createdDate">Ngày tạo</span>
          </dt>
          <dd>{apiOutEntity.createdDate ? <TextFormat value={apiOutEntity.createdDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="createdBy">Tạo bởi</span>
          </dt>
          <dd>{apiOutEntity.createdBy}</dd>
          <dt>
            <span id="lastModifiedDate">Ngày sửa đổi</span>
          </dt>
          <dd>
            {apiOutEntity.lastModifiedDate ? (
              <TextFormat value={apiOutEntity.lastModifiedDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="lastModifiedBy">Người sửa</span>
          </dt>
          <dd>{apiOutEntity.lastModifiedBy}</dd>
        </dl>
        <Button tag={Link} to="/api-out" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/api-out/${apiOutEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ApiOutDetail;
