import React, {useEffect, useState} from 'react';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Row, Col, Table} from 'reactstrap';
import {
  getSortState, Translate,
  ValidatedForm
} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {useAppDispatch, useAppSelector} from 'app/config/store';

import application, {getEntity, searchEntitiesApplication} from './application.reducer';
import {mapIdList, overridePaginationStateWithQueryParams} from 'app/shared/util/entity-utils';
import {ASC, DESC, ITEMS_PER_PAGE} from 'app/shared/util/pagination.constants';
import {DataTable} from 'primereact/datatable';
import {Column} from 'primereact/column';
import {searchEntitiesApiInfo} from "app/entities/api-info/api-info.reducer";
import {MultiSelect} from "primereact/multiselect";
import Axios from "axios";
import {searchEntitiesApiIn} from "app/entities/api-in/api-in.reducer";
import {searchEntitiesApiOut} from "app/entities/api-out/api-out.reducer";
import {searchEntitiesTopicIn} from "app/entities/topic-in/topic-in.reducer";
import {searchEntitiesTopicOut} from "app/entities/topic-out/topic-out.reducer";
import {searchEntitiesHumans} from "app/entities/humans/humans.reducer";

export const ApplicationSearch = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();
  // API INFO
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  // select multiple box
  const [valueApplication, setvalueApplication] = useState([]);
  const [valueTopic, setvalueTopic] = useState([]);
  const [valueApiInfo, setvalueApiInfo] = useState([]);
  const [valueHumans, setvalueHumans] = useState([]);

  // multiple box data
  const [multipleApiInfoList, setmultipleApiInfoList] = useState([]);
  const [multipleApplications, setmultipleApplications] = useState([]);
  const [multipleTopicList, setmultipleTopicList] = useState([]);
  const [multipleHumansList, setmultipleHumansList] = useState([]);

  // danh sách hiển thị
  const loading = useAppSelector(state => state.apiInfo.loading);
  const applicationsList = useAppSelector(state => state.application.entities);
  const apiInfoList = useAppSelector(state => state.apiInfo.entities);
  const apiInList = useAppSelector(state => state.apiIn.entities);
  const apiOutList = useAppSelector(state => state.apiOut.entities);

  const topicInList = useAppSelector(state => state.topicIn.entities);
  const topicOutList = useAppSelector(state => state.topicOut.entities);
  const humansList = useAppSelector(state => state.humans.entities);

  // load data to list box
  useEffect(() => {
    const requestUrlTopic = `api/topics?page=0&size=2000&sort=id,asc`;
    const requestUrlApplication = `api/applications?page=0&size=2000&sort=id,asc`;
    const requestUrlApiInfo = `api/api-infos?page=0&size=2000&sort=id,asc`;
    const requestUrlHumans = `api/humans?page=0&size=2000&sort=id,asc`;

    Axios.get(`${requestUrlTopic}`).then(res => {
      const data = res.data;
      setmultipleTopicList(data);
    });

    Axios.get(`${requestUrlApplication}`).then(res => {
      const data = res.data;
      setmultipleApplications(data);
    });

    Axios.get(`${requestUrlApiInfo}`).then(res => {
      const data = res.data;
      setmultipleApiInfoList(data);
    });

    Axios.get(`${requestUrlHumans}`).then(res => {
      const data = res.data;
      setmultipleHumansList(data);
    });
  }, []);

  useEffect(() => {
    if(valueApplication && valueApplication.length > 0){
      const apiIDS = apiInfoList.map((obj) => obj.id);
      handlesearchAPIIN(apiIDS);
      handlesearchAPIOUT(apiIDS);
      const appIDS = valueApplication.map((obj) => obj.id);
      handleSearchApplicationWithId(appIDS);

    }
  }, [apiInfoList]);


  useEffect(() => {
    if(valueApiInfo && valueApiInfo.length > 0){
      const appIDS = valueApiInfo.map((obj) => obj.application.id);
      handleSearchApplicationWithId(appIDS);
    }
  }, [apiInfoList]);

  const header = (title) => (
    <div className="d-flex justify-content-between p-1 mh-40">
      <div className="mr-auto p-2">
        <span className="text-xl text-900 font-bold">{title}</span>
      </div>
    </div>
  );



  const handleSeaching = () => {

    // query with owner
    if(valueHumans && valueHumans.length > 0){
      const humansIds = valueHumans.map((obj) => obj.id);
      handleSearchApplication(humansIds);
    }

      if(valueApplication !== null && valueApplication.length > 0){
        const appIDS = valueApplication.map((obj) => obj.id);
        handlesearchAPIInfo(appIDS);
      }

    if(valueApiInfo !== null && valueApiInfo.length > 0){
      const apiIDS = valueApiInfo.map((obj) => obj.id);
      const appIDS = valueApiInfo.map((obj) => obj.application.id);
      handlesearchAPIInfoWithId(apiIDS);
      handleSearchApplicationWithId(appIDS);
      handlesearchAPIIN(apiIDS);
      handlesearchAPIOUT(apiIDS);
    }

    // query topic
    if(valueTopic && valueTopic.length > 0){
      const dataTopic = valueTopic.map((obj) => obj.id);
      handlesearchTOPICIN(dataTopic);
      handlesearchTOPICOUT(dataTopic);
    }

  }

  const handleSearchApplication = (humansId) => {
    const searchCriterials = [];
    searchCriterials['humansId.in'] = humansId;
    dispatch(
      searchEntitiesApplication({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
        searchCriterials,
      })
    );
  }

  const handleSearchApplicationWithId = (appId) => {
    const searchCriterials = [];
    searchCriterials['id.in'] = appId;
    dispatch(
      searchEntitiesApplication({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
        searchCriterials,
      })
    );
  }

  const handlesearchAPIInfo = (dataApplication) => {
      const searchCriterials = [];
      searchCriterials['applicationId.in'] = dataApplication;
        dispatch(
        searchEntitiesApiInfo({
          page: paginationState.activePage - 1,
          size: paginationState.itemsPerPage,
          sort: `${paginationState.sort},${paginationState.order}`,
          searchCriterials,
        })
      );
  }

  const handlesearchAPIInfoWithId = (apiInfoId) => {
    const searchCriterials = [];
    searchCriterials['id.in'] = apiInfoId;
    dispatch(
      searchEntitiesApiInfo({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
        searchCriterials,
      })
    );
  }

  const handlesearchAPIIN = (dataApiInfo) => {
    if (dataApiInfo) {
      const searchCriterials = [];
      searchCriterials['apiInfoId.in'] = dataApiInfo;
      dispatch(
        searchEntitiesApiIn({
          page: paginationState.activePage - 1,
          size: paginationState.itemsPerPage,
          sort: `${paginationState.sort},${paginationState.order}`,
          searchCriterials,
        })
      );
    }
  }

  const handlesearchAPIOUT = (dataApiInfo) => {
    if (dataApiInfo) {
      const searchCriterials = [];
      searchCriterials['apiInfoId.in'] = dataApiInfo;
      dispatch(
        searchEntitiesApiOut({
          page: paginationState.activePage - 1,
          size: paginationState.itemsPerPage,
          sort: `${paginationState.sort},${paginationState.order}`,
          searchCriterials,
        })
      );
    }
  }

  const handlesearchTOPICIN = (dataApiInfo) => {
    if (dataApiInfo) {
      const searchCriterials = [];
      searchCriterials['topicId.in'] = dataApiInfo;
      dispatch(
        searchEntitiesTopicIn({
          page: paginationState.activePage - 1,
          size: paginationState.itemsPerPage,
          sort: `${paginationState.sort},${paginationState.order}`,
          searchCriterials,
        })
      );
    }
  }

  const handlesearchTOPICOUT = (dataApiInfo) => {
    if (dataApiInfo) {
      const searchCriterials = [];
      searchCriterials['topicId.in'] = dataApiInfo;
      dispatch(
        searchEntitiesTopicOut({
          page: paginationState.activePage - 1,
          size: paginationState.itemsPerPage,
          sort: `${paginationState.sort},${paginationState.order}`,
          searchCriterials,
        })
      );
    }
  }


  return (
    <div>
      <h3>Tìm kiếm</h3>
        <div className="d-flex justify-content-center mh-40 mt-1">
          <div>
            <div id="dd1">Nhân sự Owner</div>
            <MultiSelect
              style={{maxHeight: '40px', minWidth: '200px'}}
              value={valueHumans}
              onChange={e => {setvalueHumans(e.value),setvalueApplication([]),setvalueApiInfo([]),setvalueTopic([])}}
              options={multipleHumansList}
              optionLabel="fullName"
              filter
              showClear
              filterBy="fullName"
              maxSelectedLabels={1}
              aria-labelledby="dd1"
              className="w-full md:w-30rem"
            />
          </div>

          <div>
            <div id="dd2" style={{maxHeight: '40px', minWidth:'200px',marginLeft:'8px'}}>Ứng dụng</div>

            <MultiSelect
              style={{maxHeight: '40px', minWidth:'200px',marginLeft:'8px'}}
              value={valueApplication}
              onChange={e => {setvalueApplication(e.value),setvalueApiInfo([]),setvalueHumans([]),setvalueTopic([])}}
              options={multipleApplications}
              optionLabel="name"
              filter
              showClear
              filterBy="name"
              aria-labelledby="dd2"
              maxSelectedLabels={1}
              className="w-full md:w-30rem"
            />
          </div>

          <div>
            <div id="dd3" style={{maxHeight: '40px', minWidth:'200px',marginLeft:'8px'}}>Topic</div>
            <MultiSelect
              style={{maxHeight: '40px', minWidth: '200px',marginLeft:'8px'}}
              value={valueTopic}
              onChange={e => {setvalueTopic(e.value),setvalueApplication([]),setvalueApiInfo([]),setvalueHumans([])}}
              options={multipleTopicList}
              optionLabel="name"
              filter
              showClear
              filterBy="name"
              maxSelectedLabels={1}
              className="w-full md:w-20rem"
              aria-labelledby="dd3"
            />
          </div>

          <div>
            <div id="dd4" style={{maxHeight: '40px', minWidth:'200px',marginLeft:'8px'}}>Thông tin API</div>
            <MultiSelect
              style={{maxHeight: '40px', marginLeft: '8px',minWidth:'200px'}}
              value={valueApiInfo}
              onChange={e => {setvalueApiInfo(e.value),setvalueApplication([]),setvalueHumans([]),setvalueTopic([])} }
              options={multipleApiInfoList}
              optionLabel="name"
              filter
              showClear
              filterBy="name"
              maxSelectedLabels={1}
              className="w-full md:w-20rem"
              aria-labelledby="dd4"
            />
          </div>
          <div>
            <Button style={{maxHeight: '40px',marginTop:'18px', marginLeft: '8px',minWidth:'100px'}} color="success" type="button" onClick={handleSeaching} disabled={loading}>
              <FontAwesomeIcon icon="sync" spin={loading}/> Tìm kiếm
            </Button>
          </div>

        </div>

      {
        applicationsList && applicationsList.length > 0 && valueTopic.length === 0 ?
          <div>
            <Row style={{padding:'11px'}}>
              <div>
                <h5>Ứng dụng</h5>
              </div>
              <div className="table-responsive border" >
                {applicationsList && applicationsList.length > 0 ? (
                  <Table responsive bordered>
                    <thead>
                    <tr>
                      <th className="hand">
                        <label>STT</label>
                      </th>
                      <th className="hand">
                       <span>Tên</span>
                      </th>
                      <th className="hand">
                       <span>Môi trường</span>
                      </th>
                      <th className="hand">
                       <span>Cấu phần liên quan</span>
                      </th>

                      <th className="hand">
                        <span>Nhân sự owner</span>
                      </th>
                    </tr>
                    </thead>
                    <tbody>
                    {applicationsList.map((app, i) => (
                      <tr key={`entity-${i}`} data-cy="entityTable">
                        <td>{i + 1}</td>
                        <td>{app.name}</td>
                        <td>{app.enviroment}</td>
                        <td>{app.subComponent ? JSON.parse(app.subComponent).map((obj) => obj.name + ', ') : null}</td>
                        <td>
                          {app.humans && app.humans.map((human) => (
                            <Link key={human.id} to={`/humans/${human.id}`}>
                              <p>{human.fullName}</p>
                            </Link>
                          ))}
                        </td>
                      </tr>
                    ))}
                    </tbody>
                  </Table>
                ) : (
                  !loading && (
                    <div className="alert alert-warning">
                      <span>Không tìm thấy bản ghi</span>
                    </div>
                  )
                )}
              </div>

            </Row>
          </div>

        :null}

      {
        apiInfoList && apiInfoList.length > 0 && valueTopic.length === 0 && valueHumans.length === 0 ?
          <>
            <Row>
              <DataTable
                value={apiInfoList}
                stripedRows
                showGridlines size={'small'}
                header={header('Danh sách API')}
                tableStyle={{minWidth: '50rem'}}>
                <Column field="name" header="Tên"></Column>
                <Column field="path" header="path"></Column>
                <Column field="dateCreate" header="Ngày tạo"></Column>
                <Column field="description" header="Mô tả"></Column>
              </DataTable>
            </Row>
            <Row style={{marginTop: '8px'}}>
              <Col md="6">
                <DataTable
                  value={apiInList}
                  stripedRows
                  showGridlines size={'small'}
                  header={header('DS API ứng dụng đang gọi đến')}
                  tableStyle={{maxWidth: '50rem'}}>
                  <Column field="application.name" header="APPLICATION"></Column>
                  <Column field="apiInfo.name" header="API"></Column>
                  <Column field="apiInfo.path" header="Path"></Column>
                </DataTable>
              </Col>

              <Col md="6">
                <DataTable
                  value={apiOutList}
                  stripedRows
                  showGridlines size={'small'}
                  header={header('DS API đang gọi tới ứng dụng')}
                  tableStyle={{maxWidth: '50rem'}}>
                  <Column field="apiInfo.name" header="API"></Column>
                  <Column field="apiInfo.path" header="path"></Column>
                  <Column field="application.name" header="application"></Column>
                </DataTable>
              </Col>
            </Row>
          </>
        : null
      }

      {
        valueTopic && valueTopic.length > 0 && valueHumans.length === 0 ?
          <>
            <Row style={{marginTop: '8px'}}>
              <Col md="6">
                <DataTable
                  value={topicInList}
                  stripedRows
                  showGridlines size={'small'}
                  header={header('Consumer')}
                  tableStyle={{maxWidth: '50rem'}}>
                  <Column field="topic.name" header="TOPIC"></Column>
                  <Column field="application.name" header="application"></Column>
                </DataTable>
              </Col>

              <Col md="6">
                <DataTable
                  value={topicOutList}
                  stripedRows
                  showGridlines size={'small'}
                  header={header('Producer')}
                  tableStyle={{maxWidth: '50rem'}}>
                  <Column field="application.name" header="application"></Column>
                  <Column field="topic.name" header="TOPIC"></Column>
                </DataTable>
              </Col>
            </Row>
          </>
          : null
      }

    </div>
  );
};

export default ApplicationSearch;
