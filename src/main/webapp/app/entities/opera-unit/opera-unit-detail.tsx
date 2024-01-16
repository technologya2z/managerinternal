import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Table } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './opera-unit.reducer';

export const OperaUnitDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const operaUnitEntity = useAppSelector(state => state.operaUnit.entity);
  const applicationList = operaUnitEntity.applications;
  return (
    <div>
      <Row>
        <Col md="8">
          <h2 data-cy="operaUnitDetailsHeading">
            <Translate contentKey="cmspApp.operaUnit.detail.title">OperaUnit</Translate>
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="id">
                <Translate contentKey="global.field.id">ID</Translate>
              </span>
            </dt>
            <dd>{operaUnitEntity.id}</dd>
            <dt>
              <span id="name">
                <Translate contentKey="cmspApp.operaUnit.name">Name</Translate>
              </span>
            </dt>
            <dd>{operaUnitEntity.name}</dd>
            <dt>
              <span id="code">
                <Translate contentKey="cmspApp.operaUnit.code">Code</Translate>
              </span>
            </dt>
            <dd>{operaUnitEntity.code}</dd>
            <dt>
              <span id="address">
                <Translate contentKey="cmspApp.operaUnit.address">Address</Translate>
              </span>
            </dt>
            <dd>{operaUnitEntity.address}</dd>
            <dt>
              <span id="email">
                <Translate contentKey="cmspApp.operaUnit.email">Email</Translate>
              </span>
            </dt>
            <dd>{operaUnitEntity.email}</dd>
            <dt>
              <span id="phoneNumber">
                <Translate contentKey="cmspApp.operaUnit.phoneNumber">Phone Number</Translate>
              </span>
            </dt>
            <dd>{operaUnitEntity.phoneNumber}</dd>
            <dt>
              <span id="owner">
                <Translate contentKey="cmspApp.operaUnit.owner">Owner</Translate>
              </span>
            </dt>
            <dd>{operaUnitEntity.owner}</dd>
            <dt>
              <span id="description">
                <Translate contentKey="cmspApp.operaUnit.description">Description</Translate>
              </span>
            </dt>
            <dd>{operaUnitEntity.description}</dd>

            <dt>
              <span id="createdDate">Ngày tạo</span>
            </dt>
            <dd>
              {operaUnitEntity.createdDate ? <TextFormat value={operaUnitEntity.createdDate} type="date" format={APP_DATE_FORMAT} /> : null}
            </dd>
            <dt>
              <span id="createdBy">Tạo bởi</span>
            </dt>
            <dd>{operaUnitEntity.createdBy}</dd>
            <dt>
              <span id="lastModifiedDate">Ngày sửa</span>
            </dt>
            <dd>
              {operaUnitEntity.lastModifiedDate ? (
                <TextFormat value={operaUnitEntity.lastModifiedDate} type="date" format={APP_DATE_FORMAT} />
              ) : null}
            </dd>
            <dt>
              <span id="lastModifiedBy">Sửa bởi</span>
            </dt>
            <dd>{operaUnitEntity.lastModifiedBy}</dd>
          </dl>
          <Button tag={Link} to="/opera-unit" replace color="info" data-cy="entityDetailsBackButton">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/opera-unit/${operaUnitEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>

      <Row className={'mt-5'}>
        <h4 id="application-heading" data-cy="ApplicationHeading">
          <label>Application vận hành</label>
        </h4>
        <div className="table-responsive">
          {applicationList && applicationList.length > 0 ? (
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
                    <Translate contentKey="cmspApp.application.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand">
                    <Translate contentKey="cmspApp.application.linkSourceRepository">Link Source Repository</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand">
                    <Translate contentKey="cmspApp.application.linkCmRepository">Link Cm Repository</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand">
                    <Translate contentKey="cmspApp.application.serverlive">Serverlive</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand">
                    <Translate contentKey="cmspApp.application.serveruat">Serveruat</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand">
                    <Translate contentKey="cmspApp.application.enviroment">Enviroment</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand">
                    <Translate contentKey="cmspApp.application.subComponent">Sub Component</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand">
                    <Translate contentKey="cmspApp.application.epicJira">Epic Jira</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand">
                    <Translate contentKey="cmspApp.application.dateStart">Date Start</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand">
                    <Translate contentKey="cmspApp.application.appType">App Type</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand">
                    <Translate contentKey="cmspApp.application.dateGolive">Date Golive</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="cmspApp.application.humans">Humans</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {applicationList.map((application, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>{i + 1}</td>
                    <td>{application.name}</td>
                    <td>{application.description}</td>
                    <td className={'text-truncate'}>{application.linkSourceRepository}</td>
                    <td>{application.linkCmRepository}</td>
                    <td>{application.serverlive}</td>
                    <td>{application.serveruat}</td>
                    <td>{application.enviroment}</td>
                    <td>{application.subComponent}</td>
                    <td>{application.epicJira}</td>
                    <td>
                      {application.dateStart ? (
                        <TextFormat type="date" value={application.dateStart} format={APP_LOCAL_DATE_FORMAT} />
                      ) : null}
                    </td>
                    <td>
                      <Translate contentKey={`cmspApp.ApplicationType.${application.appType}`} />
                    </td>
                    <td>
                      {application.dateGolive ? (
                        <TextFormat type="date" value={application.dateGolive} format={APP_LOCAL_DATE_FORMAT} />
                      ) : null}
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

export default OperaUnitDetail;
