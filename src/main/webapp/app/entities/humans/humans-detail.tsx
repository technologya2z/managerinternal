import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, { useEffect, useState } from 'react';
import { TextFormat, Translate, getSortState } from 'react-jhipster';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { searchEntitiesApplication } from '../application/application.reducer';
import { getEntity } from './humans.reducer';

export const HumansDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();
  const humansEntity = useAppSelector(state => state.humans.entity);

  const [paginationStateApplication, setPaginationStateApplication] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
    handleFilterApplication(props.match.params.id);
  }, []);

  const handleFilterApplication = values => {
    const searchCriterials = [];
    if (values['humans_id']) {
      searchCriterials['humans_id.equals'] = values['humans_id'];
    }

    dispatch(
      searchEntitiesApplication({
        page: paginationStateApplication.activePage - 1,
        size: paginationStateApplication.itemsPerPage,
        sort: `${paginationStateApplication.sort},${paginationStateApplication.order}`,
        searchCriterials,
      })
    );
  };

  return (
    <div>
      <Row>
        <Col md="8">
          <h2 data-cy="humansDetailsHeading">
            <Translate contentKey="cmspApp.humans.detail.title">Humans</Translate>
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="id">
                <Translate contentKey="global.field.id">ID</Translate>
              </span>
            </dt>
            <dd>{humansEntity.id}</dd>
            <dt>
              <span id="fullName">
                <Translate contentKey="cmspApp.humans.fullName">Full Name</Translate>
              </span>
            </dt>
            <dd>{humansEntity.fullName}</dd>
            <dt>
              <span id="code">
                <Translate contentKey="cmspApp.humans.code">Code</Translate>
              </span>
            </dt>
            <dd>{humansEntity.code}</dd>
            <dt>
              <span id="userName">
                <Translate contentKey="cmspApp.humans.userName">User Name</Translate>
              </span>
            </dt>
            <dd>{humansEntity.userName}</dd>
            <dt>
              <span id="email">
                <Translate contentKey="cmspApp.humans.email">Email</Translate>
              </span>
            </dt>
            <dd>{humansEntity.email}</dd>
            <dt>
              <span id="active">
                <Translate contentKey="cmspApp.humans.active">Active</Translate>
              </span>
            </dt>
            <dd>{humansEntity.active}</dd>
            <dt>
              <span id="phoneNumber">
                <Translate contentKey="cmspApp.humans.phoneNumber">Phone Number</Translate>
              </span>
            </dt>
            <dd>{humansEntity.phoneNumber}</dd>
            <dt>
              <span id="dateOfBirth">
                <Translate contentKey="cmspApp.humans.dateOfBirth">Date Of Birth</Translate>
              </span>
            </dt>

            <dd>{<TextFormat value={humansEntity.dateOfBirth} type="date" format={APP_LOCAL_DATE_FORMAT} />}</dd>
            <dt>
              <span id="homePhome">
                <Translate contentKey="cmspApp.humans.homePhome">Home Phome</Translate>
              </span>
            </dt>
            <dd>{humansEntity.homePhome}</dd>
            <dt>
              <span id="address">
                <Translate contentKey="cmspApp.humans.address">Address</Translate>
              </span>
            </dt>
            <dd>{humansEntity.address}</dd>
            <dt>
              <span id="wards">
                <Translate contentKey="cmspApp.humans.wards">Wards</Translate>
              </span>
            </dt>
            <dd>{humansEntity.wards}</dd>
            <dt>
              <span id="district">
                <Translate contentKey="cmspApp.humans.district">District</Translate>
              </span>
            </dt>
            <dd>{humansEntity.district}</dd>
            <dt>
              <span id="province">
                <Translate contentKey="cmspApp.humans.province">Province</Translate>
              </span>
            </dt>
            <dd>{humansEntity.province}</dd>
            <dt>
              <span id="joinDate">
                <Translate contentKey="cmspApp.humans.joinDate">Join Date</Translate>
              </span>
            </dt>
            <dd>{<TextFormat value={humansEntity.joinDate} type="date" format={APP_LOCAL_DATE_FORMAT} />}</dd>
            <dt>
              <span id="married">
                <Translate contentKey="cmspApp.humans.married">Married</Translate>
              </span>
            </dt>
            <dd>{humansEntity.married}</dd>
            <dt>
              <span id="gender">
                <Translate contentKey="cmspApp.humans.gender">Gender</Translate>
              </span>
            </dt>
            <dd>{humansEntity.gender}</dd>
            <dt>
              <span id="description">
                <Translate contentKey="cmspApp.humans.description">Description</Translate>
              </span>
            </dt>
            <dd>{humansEntity.description}</dd>
            <dt>
              <Translate contentKey="cmspApp.humans.jobtitle">Jobtitle</Translate>
            </dt>
            <dd>
              {humansEntity.jobtitles
                ? humansEntity.jobtitles.map((val, i) => (
                    <span key={val.id}>
                      <label>{val.name}</label>
                      {humansEntity.jobtitles && i === humansEntity.jobtitles.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
            <dt>
              <Translate contentKey="cmspApp.humans.department">Department</Translate>
            </dt>
            <dd>{humansEntity.department ? humansEntity.department.name : ''}</dd>

            <dt>
              <span id="createdDate">Created Date</span>
            </dt>
            <dd>
              {humansEntity.createdDate ? <TextFormat value={humansEntity.createdDate} type="date" format={APP_DATE_FORMAT} /> : null}
            </dd>
            <dt>
              <span id="createdBy">Created By</span>
            </dt>
            <dd>{humansEntity.createdBy}</dd>
            <dt>
              <span id="lastModifiedDate">Last Modified Date</span>
            </dt>
            <dd>
              {humansEntity.lastModifiedDate ? (
                <TextFormat value={humansEntity.lastModifiedDate} type="date" format={APP_DATE_FORMAT} />
              ) : null}
            </dd>
            <dt>
              <span id="lastModifiedBy">Last Modified By</span>
            </dt>
            <dd>{humansEntity.lastModifiedBy}</dd>
          </dl>
          <Button tag={Link} to="/humans" replace color="info" data-cy="entityDetailsBackButton">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/humans/${humansEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
      <Row className={'mt-5'}>
        <h4 id="application-heading" data-cy="ApplicationHeading">
          <label>Application</label>
        </h4>
        <div className="table-responsive">
          {humansEntity.applications && humansEntity.applications.length > 0 ? (
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
                {humansEntity.applications.map((application, i) => (
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

export default HumansDetail;
