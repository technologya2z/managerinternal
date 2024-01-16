import React, { useEffect, useState } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { getEntities as getDatabaseInfos } from 'app/entities/database-info/database-info.reducer';
import { getEntities as getHumans } from 'app/entities/humans/humans.reducer';
import { getEntities as getOperaUnits } from 'app/entities/opera-unit/opera-unit.reducer';
import { getEntities as getTopics } from 'app/entities/topic/topic.reducer';
import { ApplicationType } from 'app/shared/model/enumerations/application-type.model';
import { findObjects, isEmptyObject, mapIdObjectList } from 'app/shared/util/entity-utils';
import { MultiSelect } from 'primereact/multiselect';
import { Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { toast } from 'react-toastify';
import { createEntity, getEntities as getapplicationList, getEntity, reset, updateEntity } from './application.reducer';
import error = toast.error;

export const ApplicationUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);
  const [valueSubcomponent, setvalueSubcomponent] = useState([]);
  const [valueOperaUnit, setvalueOperaUnit] = useState([]);
  const [valueDatabaseinfo, setvalueDatabaseinfo] = useState([]);
  const [valueTopic, setvalueTopic] = useState([]);
  const [valueHuman, setvalueHuman] = useState([]);

  const topics = useAppSelector(state => state.topic.entities);
  const operaUnits = useAppSelector(state => state.operaUnit.entities);
  const databaseInfos = useAppSelector(state => state.databaseInfo.entities);
  const humans = useAppSelector(state => state.humans.entities);
  const applicationEntity = useAppSelector(state => state.application.entity);
  const loading = useAppSelector(state => state.application.loading);
  const updating = useAppSelector(state => state.application.updating);
  const updateSuccess = useAppSelector(state => state.application.updateSuccess);
  const applicationTypeValues = Object.keys(ApplicationType);
  const applicationList = useAppSelector(state => state.application.entities);
  const handleClose = () => {
    props.history.push('/application' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }
    dispatch(getapplicationList({ page: 0, size: 1000, sort: 'id,asc' }));
    dispatch(getTopics({ page: 0, size: 1000, sort: 'id,asc' }));
    dispatch(getOperaUnits({ page: 0, size: 1000, sort: 'id,asc' }));
    dispatch(getDatabaseInfos({ page: 0, size: 1000, sort: 'id,asc' }));
    dispatch(getHumans({ page: 0, size: 1000, sort: 'id,asc' }));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  useEffect(() => {
    if (!isEmptyObject(applicationEntity) && !isEmptyObject(applicationList)) {
      applicationEntity.operaunits ? setvalueOperaUnit(findObjects(operaUnits, applicationEntity.operaunits)) : null;

      applicationEntity.databaseinfos ? setvalueDatabaseinfo(findObjects(databaseInfos, applicationEntity.databaseinfos)) : null;

      applicationEntity.topics ? setvalueTopic(findObjects(topics, applicationEntity.topics)) : null;

      applicationEntity.humans ? setvalueHuman(findObjects(humans, applicationEntity.humans)) : null;

      if (applicationEntity.subComponent) {
        const arr = JSON.parse(applicationEntity.subComponent).map((obj) => obj.id);
        const arrlist = [];
        for (let i = 0; i < applicationList.length; i++) {
          if (arr.includes(applicationList[i].id)) {
            arrlist.push(applicationList[i]);
          }
        }
        setvalueSubcomponent(arrlist);
      }
    }
  }, [applicationEntity, applicationList, operaUnits, databaseInfos, topics, humans]);

  const saveEntity = values => {

    const arrSub = [];
    valueSubcomponent.map(otherEntity => (
      arrSub.push({id: otherEntity.id,name: otherEntity.name})
    ));
    const entity = {
      ...applicationEntity,
      ...values,
      topics: mapIdObjectList(valueTopic),
      operaunits: mapIdObjectList(valueOperaUnit),
      databaseinfos: mapIdObjectList(valueDatabaseinfo),
      subComponent: JSON.stringify(arrSub),
      humans: mapIdObjectList(valueHuman),
    };
    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          // appType: 'PVCOMBANK',
          ...applicationEntity,
          topics: applicationEntity?.topics?.map(e => e.id.toString()),
          databaseinfos: applicationEntity?.databaseinfos?.map(e => e.id.toString()),
          humans: applicationEntity?.humans?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="cmspApp.application.home.createOrEditLabel" data-cy="ApplicationCreateUpdateHeading">
            {isNew ?
              <span> Tạo mới thông tin ứng dụng </span>
              : <span> Chỉnh sửa thông tin ứng dụng </span> }
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="application-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField label={translate('cmspApp.application.name')} id="application-name" name="name" data-cy="name" type="text" />
              <ValidatedField label={translate('cmspApp.application.code')} id="application-code" name="code" data-cy="code" type="text" />
              <ValidatedField
                label={translate('cmspApp.application.linkSourceRepository')}
                id="application-linkSourceRepository"
                name="linkSourceRepository"
                data-cy="linkSourceRepository"
                type="text"
              />
              <ValidatedField
                label={translate('cmspApp.application.linkCmRepository')}
                id="application-linkCmRepository"
                name="linkCmRepository"
                data-cy="linkCmRepository"
                type="text"
              />
              <ValidatedField
                label={translate('cmspApp.application.serverlive')}
                id="application-serverlive"
                name="serverlive"
                data-cy="serverlive"
                type="text"
              />
              <ValidatedField
                label={translate('cmspApp.application.serveruat')}
                id="application-serveruat"
                name="serveruat"
                data-cy="serveruat"
                type="text"
              />
              <ValidatedField
                label={translate('cmspApp.application.linkLive')}
                id="application-linkLive"
                name="linkLive"
                data-cy="linkLive"
                type="text"
              />
              <ValidatedField
                label={translate('cmspApp.application.linkUat')}
                id="application-linkUat"
                name="linkUat"
                data-cy="linkUat"
                type="text"
              />
              <ValidatedField
                label={translate('cmspApp.application.enviroment')}
                id="application-enviroment"
                name="enviroment"
                data-cy="enviroment"
                type="text"
              />
              <div style={{ width: '100%' }}>
                <label>Cấu phần liên quan</label>
                <br />
                <MultiSelect
                  value={valueSubcomponent}
                  onChange={e => setvalueSubcomponent(e.value)}
                  options={applicationList}
                  optionLabel="name"
                  filter
                  showClear
                  filterBy="name"
                  placeholder="chọn ứng dụng"
                  maxSelectedLabels={20}
                  className="w-full md:w-20rem d-flex"
                />
              </div>
              <ValidatedField
                label={translate('cmspApp.application.epicJira')}
                id="application-epicJira"
                name="epicJira"
                data-cy="epicJira"
                type="text"
              />
              <ValidatedField
                label={translate('cmspApp.application.swaggerLink')}
                id="application-swaggerLink"
                name="swaggerLink"
                data-cy="swaggerLink"
                type="text"
              />
              <ValidatedField
                label={translate('cmspApp.application.dateStart')}
                id="application-dateStart"
                name="dateStart"
                data-cy="dateStart"
                type="date"
              />
              <ValidatedField
                label={translate('cmspApp.application.appType')}
                id="application-appType"
                name="appType"
                data-cy="appType"
                type="select"
              >
                <option value="" key="0" />
               <option value="PARTNER">Đối tác</option>
               <option value="PVCOMBANK">Nội bộ</option>
                {/* <option value={'1'} key={'nam'}>
                  {'nam'}
                </option>
                <option value={'nữ'} key={'nữ'}>
                  {'nữ'}
                </option> */}
              </ValidatedField>
              <ValidatedField
                label={translate('cmspApp.application.dateGolive')}
                id="application-dateGolive"
                name="dateGolive"
                data-cy="dateGolive"
                type="date"
              />
              <div style={{ width: '100%' }}>
                <label>{translate('cmspApp.application.operaunit')}</label>
                <br />
                <MultiSelect
                  value={valueOperaUnit}
                  onChange={e => setvalueOperaUnit(e.value)}
                  options={operaUnits}
                  optionLabel="name"
                  filter
                  showClear
                  filterBy="name"
                  placeholder="chọn đơn vị vận hành"
                  maxSelectedLabels={20}
                  className="w-full md:w-20rem d-flex"
                />
              </div>
              <div style={{ width: '100%' }}>
                <label>{translate('cmspApp.application.databaseinfo')}</label>
                <br />
                <MultiSelect
                  value={valueDatabaseinfo}
                  onChange={e => setvalueDatabaseinfo(e.value)}
                  options={databaseInfos}
                  optionLabel="name"
                  filter
                  showClear
                  filterBy="name"
                  placeholder="chọn database"
                  maxSelectedLabels={20}
                  className="w-full md:w-20rem d-flex"
                />
              </div>
              <div style={{ width: '100%' }}>
                <label>{translate('cmspApp.application.topics')}</label>
                <br />
                <MultiSelect
                  value={valueTopic}
                  onChange={e => setvalueTopic(e.value)}
                  options={topics}
                  optionLabel="name"
                  filter
                  showClear
                  filterBy="name"
                  placeholder="chọn topic"
                  maxSelectedLabels={20}
                  className="w-full md:w-20rem d-flex"
                />
              </div>
              <div style={{ width: '100%' }}>
                <label>{translate('cmspApp.application.humans')}</label>
                <br />
                <MultiSelect
                  value={valueHuman}
                  onChange={e => setvalueHuman(e.value)}
                  options={humans}
                  optionLabel="fullName"
                  filter
                  showClear
                  filterBy="fullName"
                  placeholder="chọn nhân sự owner"
                  maxSelectedLabels={20}
                  className="w-full md:w-20rem d-flex"
                />
              </div>
              <ValidatedField
                label={translate('cmspApp.application.description')}
                id="application-description"
                name="description"
                data-cy="description"
                type="textarea"
                style={{ minHeight: '200px' }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/application" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default ApplicationUpdate;
