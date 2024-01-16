import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, { useEffect, useState } from 'react';
import { TextFormat, Translate, getSortState } from 'react-jhipster';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { searchEntitiesApiInfo } from 'app/entities/api-info/api-info.reducer';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { ASC, DESC, ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { getEntity } from './application.reducer';

import { FilterMatchMode } from 'primereact/api';
import { Column } from 'primereact/column';
import { DataTable } from 'primereact/datatable';

import ModalAPI from 'app/entities/application/modal-api';
import ModalTopic from 'app/entities/application/modal-topic';
import { InputText } from 'primereact/inputtext';

export const ApplicationDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();
  // API INFO
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );
  const [modalApiIsOpen, setmodalApiIsOpen] = useState(false);
  const [modalTopicIsOpen, setModalTopicIsOpen] = useState(false);

  const [idApiInfo, setIdApiInfo] = useState();
  const [idTopic, setIdTopic] = useState();
  const [apiInfoList, setApiInfoList] = useState([]);

  const [globalFilterValue, setGlobalFilterValue] = useState('');
  const [filters, setFilters] = useState({
    global: { value: null, matchMode: FilterMatchMode.CONTAINS },
    name: { value: null, matchMode: FilterMatchMode.STARTS_WITH }
  });



  const loading = useAppSelector(state => state.apiInfo.loading);
  const applicationEntity = useAppSelector(state => state.application.entity);
  const totalItems = useAppSelector(state => state.application.totalItems);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleFilter = () => {
    const searchCriterials = [];
    searchCriterials['applicationId.equals'] = props.match.params.id;
    dispatch(
      searchEntitiesApiInfo({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
        searchCriterials,
      })
    );
  };

  const handleModalAPI = value => {
    setmodalApiIsOpen(true);
    setIdApiInfo(value);
  };

  const handleModalTopic = value => {
    setModalTopicIsOpen(true);
    setIdTopic(value);
  };

  useEffect(() => {
    handleFilter();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);
  // end
  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  useEffect(() => {
    if(applicationEntity){
      setApiInfoList(applicationEntity.apiInfos)
    }
  }, [applicationEntity]);

  const header = (title) => (
    <div className="flex justify-content-between p-2 mr2">
      <span className="text-xl text-900 font-bold">{title}</span>
      <InputText value={globalFilterValue} onChange={onGlobalFilterChange} placeholder="Tên ứng dụng" />
    </div>
  );

  const onGlobalFilterChange = (e) => {
    const value = e.target.value;
    const _filters = { ...filters };

    _filters['global'].value = value;

    setFilters(_filters);
    setGlobalFilterValue(value);
  };


  return (
    <div>
      <h2 data-cy="applicationDetailsHeading">
            <Translate contentKey="cmspApp.application.detail.title">Application</Translate>
          </h2>

      <Row>
        <Col md="4">
          <dl className="jh-entity-details">
            <dt>
              <span id="id">
                <Translate contentKey="global.field.id">ID</Translate>
              </span>
            </dt>
            <dd>{applicationEntity.id}</dd>
            <dt>
              <span id="name">
                <Translate contentKey="cmspApp.application.name">Name</Translate>
              </span>
            </dt>
            <dd>{applicationEntity.name}</dd>
            <dt>
              <span id="code">
                <Translate contentKey="cmspApp.application.code">Code</Translate>
              </span>
            </dt>
            <dd>{applicationEntity.code}</dd>
            <dt>
              <span id="description">
                <Translate contentKey="cmspApp.application.description">Description</Translate>
              </span>
            </dt>
            <dd>{applicationEntity.description}</dd>
            <dt>
              <span id="linkSourceRepository">
                <Translate contentKey="cmspApp.application.linkSourceRepository">Link Source Repository</Translate>
              </span>
            </dt>
            <dd>{applicationEntity.linkSourceRepository}</dd>
            <dt>
              <span id="linkCmRepository">
                <Translate contentKey="cmspApp.application.linkCmRepository">Link Cm Repository</Translate>
              </span>
            </dt>
            <dd>{applicationEntity.linkCmRepository}</dd>
            <dt>
              <span id="serverlive">
                <Translate contentKey="cmspApp.application.serverlive">Serverlive</Translate>
              </span>
            </dt>
            <dd>{applicationEntity.serverlive}</dd>
          </dl>
        </Col>

        <Col md="4">
          <dl className="jh-entity-details">
            <dt>
              <span id="linkLive">
                <Translate contentKey="cmspApp.application.linkLive">linkLive</Translate>
              </span>
            </dt>
            <dd>{applicationEntity.linkLive}</dd>

            <dt>
              <span id="linkUat">
                <Translate contentKey="cmspApp.application.linkUat">linkUat</Translate>
              </span>
            </dt>
            <dd>{applicationEntity.linkUat}</dd>

            <dt>
              <span id="enviroment">
                <Translate contentKey="cmspApp.application.enviroment">Enviroment</Translate>
              </span>
            </dt>
            <dd>{applicationEntity.enviroment}</dd>
            <dt>
              <span id="subComponent">
                <Translate contentKey="cmspApp.application.subComponent">Sub Component</Translate>
              </span>
            </dt>
            <dd>{applicationEntity.subComponent ? JSON.parse(applicationEntity.subComponent).map((obj) => obj.name+' - ')  : null}</dd>
            <dt>
              <span id="epicJira">
                <Translate contentKey="cmspApp.application.epicJira">Epic Jira</Translate>
              </span>
            </dt>
            <dd>{applicationEntity.epicJira}</dd>
            <dt>
              <span id="dateStart">
                <Translate contentKey="cmspApp.application.dateStart">Date Start</Translate>
              </span>
            </dt>
            <dd>
              {applicationEntity.dateStart ? (
                <TextFormat value={applicationEntity.dateStart} type="date" format={APP_LOCAL_DATE_FORMAT} />
              ) : null}
            </dd>
            <dt>
              <span id="appType">
                <Translate contentKey="cmspApp.application.appType">App Type</Translate>
              </span>
            </dt>
            <dd>{applicationEntity.appType}</dd>
          </dl>
          <dt>
              <span id="dateGolive">
                <Translate contentKey="cmspApp.application.dateGolive">Date Golive</Translate>
              </span>
          </dt>
          <dd>
            {applicationEntity.dateGolive ? (
              <TextFormat value={applicationEntity.dateGolive} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
              <span id="serveruat">
                <Translate contentKey="cmspApp.application.serveruat">Serveruat</Translate>
              </span>
          </dt>
          <dd>{applicationEntity.serveruat}</dd>
        </Col>

        <Col md="4">
          <dl className="jh-entity-details">
            <dt>
              <Translate contentKey="cmspApp.application.databaseInfo">Database Info</Translate>
            </dt>
            <dd>
              {applicationEntity.databaseinfos
                ? applicationEntity.databaseinfos.map((val, i) => (
                  <span key={val.id}>
                      <a href={'database-info/' + val.id}>{val.name}</a>
                    {applicationEntity.databaseinfos && i === applicationEntity.databaseinfos.length - 1 ? '' : ', '}
                    </span>
                ))
                : null}
            </dd>

            <dt>
              <Translate contentKey="cmspApp.application.topics">Topics</Translate>
            </dt>
            <dd>
              {applicationEntity.topics
                ? applicationEntity.topics.map((val, i) => (
                  <span key={val.id}>
                      <a href={'topic/' + val.id}>{val.name}</a>
                    {applicationEntity.topics && i === applicationEntity.topics.length - 1 ? '' : ', '}
                    </span>
                ))
                : null}
            </dd>

            <dt>
              <Translate contentKey="cmspApp.application.operaunit">Operaunit</Translate>
            </dt>
            <dd>
              {applicationEntity.operaunits
                ? applicationEntity.operaunits.map((val, i) => (
                  <span key={val.id}>
                      <a href={'opera-unit/' + val.id}>{val.name}</a>
                    {applicationEntity.operaunits && i === applicationEntity.operaunits.length - 1 ? '' : ', '}
                    </span>
                ))
                : null}
            </dd>
            <dt>
              <Translate contentKey="cmspApp.application.humans">Humans</Translate>
            </dt>
            <dd>
              {applicationEntity.humans
                ? applicationEntity.humans.map((val, i) => (
                  <span key={val.id}>
                      <a href={val ? 'humans/' + val?.id : ''}>{val?.fullName}</a>
                    </span>
                ))
                : null}
            </dd>
            <dt>
              <span id="createdDate">Ngày tạo</span>
            </dt>
            <dd>
              {applicationEntity.createdDate ? (
                <TextFormat value={applicationEntity.createdDate} type="date" format={APP_DATE_FORMAT} />
              ) : null}
            </dd>
            <dt>
              <span id="createdBy">Tạo bởi</span>
            </dt>
            <dd>{applicationEntity.createdBy}</dd>
            <dt>
              <span id="lastModifiedDate">Ngày sửa đổi</span>
            </dt>
            <dd>
              {applicationEntity.lastModifiedDate ? (
                <TextFormat value={applicationEntity.lastModifiedDate} type="date" format={APP_DATE_FORMAT} />
              ) : null}
            </dd>
            <dt>
              <span id="lastModifiedBy">Ngưởi sửa đổi</span>
            </dt>
            <dd>{applicationEntity.lastModifiedBy}</dd>
          </dl>
        </Col>

      </Row>


      <Button tag={Link} to="/application" replace color="info" data-cy="entityDetailsBackButton">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/application/${applicationEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>

      <Row className="mt-4">
          <DataTable
            filters={filters} filterDisplay="row"
            paginator
            rows={10}
            rowsPerPageOptions={[5, 10, 25, 50]}
            value={apiInfoList}
            showGridlines size={'small'}
            header={header('Danh sách API ')}
            tableStyle={{ minWidth: '50rem' }}>
            <Column field="name" header="Tên"></Column>
            <Column field="path" header="path"></Column>
            <Column field="dateCreate" header="Ngày tạo"></Column>
            <Column field="description" header="Mô tả"></Column>
          </DataTable>
      </Row>


      <Row>
        <h4>
          <Translate contentKey="cmspApp.topic.home.title">TOPIC</Translate>
        </h4>
        <div className="table-responsive">
          {applicationEntity.topics && applicationEntity.topics.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={sort('name')}>
                    <Translate contentKey="cmspApp.topic.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('bootrapServer')}>
                    <Translate contentKey="cmspApp.topic.bootrapServer">Bootrap Server</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('locationTopic')}>
                    <Translate contentKey="cmspApp.topic.locationTopic">Location Topic</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('rootproducer')}>
                    <Translate contentKey="cmspApp.topic.rootproducer">Rootproducer</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('rootTable')}>
                    <Translate contentKey="cmspApp.topic.rootTable">Root Table</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {applicationEntity.topics.map((topic, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>{topic.name}</td>
                    <td>{topic.bootrapServer}</td>
                    <td>{topic.locationTopic}</td>
                    <td>{topic.rootproducer}</td>
                    <td>{topic.rootTable}</td>
                    <td className="text-end">
                      <div className="btn-group flex-btn-group-container">
                        <Button onClick={() => handleModalTopic(topic.id)} color="info" size="sm" data-cy="entityDetailsButton">
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
            !loading && (
              <div className="alert alert-warning">
                <Translate contentKey="cmspApp.topic.home.notFound">No Topics found</Translate>
              </div>
            )
          )}
        </div>
      </Row>

      <ModalAPI props={props} isOpen={modalApiIsOpen} onClose={() => setmodalApiIsOpen(false)} idApiInfo={idApiInfo} />
      <ModalTopic props={props} isOpen={modalTopicIsOpen} onClose={() => setModalTopicIsOpen(false)} idTopic={idTopic} />
    </div>
  );
};

export default ApplicationDetail;
