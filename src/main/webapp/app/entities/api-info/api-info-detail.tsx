import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, {useEffect, useState} from 'react';
import {getSortState, TextFormat, Translate} from 'react-jhipster';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './api-info.reducer';
import {overridePaginationStateWithQueryParams} from "app/shared/util/entity-utils";
import {ITEMS_PER_PAGE} from "app/shared/util/pagination.constants";

export const ApiInfoDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [applicationINS, setapplicationINS] = useState([]);
  const [applicationOUTS, setapplicationOUTS] = useState([]);

  const apiInfoEntity = useAppSelector(state => state.apiInfo.entity);

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  useEffect(() => {
    const requestUrlApplication = `api/applications?page=0&size=2000&sort=id,asc`;
  }, [apiInfoEntity]);


  return (
    <div>
       <h2 data-cy="apiInfoDetailsHeading">
            <Translate contentKey="cmspApp.apiInfo.detail.title">ApiInfo</Translate>
          </h2>

      <Row className='distance justify-content-center'>
        <Col md="4">
          <dl className="jh-entity-details">
            <dt>
              <span id="id">
                <Translate contentKey="global.field.id">ID</Translate>
              </span>
            </dt>
            <dd>{apiInfoEntity.id}</dd>
            <dt>
              <span id="name">
                <Translate contentKey="cmspApp.apiInfo.name">Name</Translate>
              </span>
            </dt>
            <dd>{apiInfoEntity.name}</dd>

            <dt>
              <span id="method">
                <Translate contentKey="cmspApp.apiInfo.method">method</Translate>
              </span>
            </dt>
            <dd>{apiInfoEntity.method}</dd>
            </dl>
          </Col>
          <Col md="4">
              <dl className="jh-entity-details">
            <dt>
              <span id="path">
                <Translate contentKey="cmspApp.apiInfo.path">Path</Translate>
              </span>
            </dt>
            <dd>{apiInfoEntity.path}</dd>
            <dt>
              <span id="requestExample">
                <Translate contentKey="cmspApp.apiInfo.requestExample">Request Example</Translate>
              </span>
            </dt>
            <dd>{apiInfoEntity.requestExample}</dd>

            <dt>
              <span id="dateCreate">
                <Translate contentKey="cmspApp.apiInfo.dateCreate">Date Create</Translate>
              </span>
            </dt>
            <dd>
              {apiInfoEntity.dateCreate ? <TextFormat value={apiInfoEntity.dateCreate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
            </dd>
            </dl>
            </Col>
            <Col md="4">
           <dl className="jh-entity-details">
            <dt>
              <span id="description">
                <Translate contentKey="cmspApp.apiInfo.description">Description</Translate>
              </span>
            </dt>
            <dd>{apiInfoEntity.description}</dd>
            <dt>
              <Translate contentKey="cmspApp.apiInfo.application">Application</Translate>
            </dt>
            <dd>{apiInfoEntity.application ? apiInfoEntity.application.name : ''}</dd>

            <dt>
              <span id="responseExample">
                <Translate contentKey="cmspApp.apiInfo.responseExample">Response Example</Translate>
              </span>
            </dt>
            <dd>{apiInfoEntity.responseExample}</dd>
          </dl>
        </Col>
      </Row>
            <Button tag={Link} to="/api-info" replace color="info" data-cy="entityDetailsBackButton">
              <FontAwesomeIcon icon="arrow-left" />{' '}
              <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
            </Button>
            &nbsp;
            <Button tag={Link} to={`/api-info/${apiInfoEntity.id}/edit`} replace color="primary">
              <FontAwesomeIcon icon="pencil-alt" />{' '}
              <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
            </Button>

      <Row>
        <h3 id="application-heading" data-cy="ApplicationHeading">
          Ứng dụng call vào
        </h3>
        <div className="table-responsive">
          {apiInfoEntity.applicationIns && apiInfoEntity.applicationIns.length > 0 ? (
            <Table responsive bordered>
              <thead>
                <tr>
                  <th className="hand">
                    <label>STT</label>
                  </th>
                  <th className="hand">
                    <Translate contentKey="cmspApp.application.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>

                  <th className="hand">
                    <Translate contentKey="cmspApp.application.enviroment">Enviroment</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand">
                    <Translate contentKey="cmspApp.application.subComponent">Sub Component</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand">
                    <Translate contentKey="cmspApp.application.appType">App Type</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="cmspApp.application.humans">Humans</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                </tr>
              </thead>
              <tbody>
                {apiInfoEntity.applicationIns.map((application, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>{i + 1}</td>
                    <td>{application.name}</td>
                    <td>{application.enviroment}</td>
                    <td>{application.subComponent ? JSON.parse(application.subComponent).map((obj) => obj.name+',')  : null}</td>
                    <td>
                      <Translate contentKey={`cmspApp.ApplicationType.${application.appType}`} />
                    </td>
                    <td>{application.humans ? <Link to={`/humans/${application.humans.id}`}>{application.humans.fullName}</Link> : ''}</td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">
              <Translate contentKey="cmspApp.application.home.notFound">No Applications found</Translate>
            </div>
          )}
        </div>
      </Row>

      <Row>
        <h3 id="application-heading" data-cy="ApplicationHeading">
          Đang call đến ứng dụng
        </h3>
        <div className="table-responsive">
          {apiInfoEntity.applicationOuts && apiInfoEntity.applicationOuts.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand">
                    <label>STT</label>
                  </th>
                  <th className="hand">
                    <Translate contentKey="cmspApp.application.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>

                  <th className="hand">
                    <Translate contentKey="cmspApp.application.enviroment">Enviroment</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand">
                    <Translate contentKey="cmspApp.application.subComponent">Sub Component</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand">
                    <Translate contentKey="cmspApp.application.appType">App Type</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="cmspApp.application.humans">Humans</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {apiInfoEntity.applicationOuts.map((application, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>{i + 1}</td>
                    <td>{application.name}</td>
                    <td>{application.enviroment}</td>
                    <td>{application.subComponent ? JSON.parse(application.subComponent).map((obj) => obj.name+',')  : null}</td>
                    <td>
                      <Translate contentKey={`cmspApp.ApplicationType.${application.appType}`} />
                    </td>
                    <td>{application.humans ? <Link to={`/humans/${application.humans.id}`}>{application.humans.fullName}</Link> : ''}</td>
                    <td className="text-end">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`/application/${application.id}`} color="info" size="sm" data-cy="entityDetailsButton">
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
              <Translate contentKey="cmspApp.application.home.notFound">No Applications found</Translate>
            </div>
          )}
        </div>
      </Row>
    </div>
  );
};

export default ApiInfoDetail;
