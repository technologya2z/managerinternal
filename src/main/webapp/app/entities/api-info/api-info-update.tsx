import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { findObjects, isEmptyObject, mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IApplication } from 'app/shared/model/application.model';
import { getEntities as getApplications } from 'app/entities/application/application.reducer';
import { IApiInfo } from 'app/shared/model/api-info.model';
import { getEntity, updateEntity, createEntity, reset } from './api-info.reducer';
import { MultiSelect } from 'primereact/multiselect';

export const ApiInfoUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);
  const [valueApplication, settvalueApplication] = useState([]);

  const applicationList = useAppSelector(state => state.application.entities);
  const apiInfoEntity = useAppSelector(state => state.apiInfo.entity);
  const loading = useAppSelector(state => state.apiInfo.loading);
  const updating = useAppSelector(state => state.apiInfo.updating);
  const updateSuccess = useAppSelector(state => state.apiInfo.updateSuccess);
  const handleClose = () => {
    props.history.push('/api-info' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }
    dispatch(getApplications({ page: 0, size: 1000, sort: 'id,asc' }));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  useEffect(() => {
    if (!isEmptyObject(applicationList) && !isEmptyObject(apiInfoEntity)) {
      if (apiInfoEntity.application) {
        const arr = [];
        arr.push(apiInfoEntity.application);
        settvalueApplication(findObjects(applicationList, arr));
      }
    }
  }, [apiInfoEntity, applicationList]);

  const saveEntity = values => {
    const entity = {
      ...apiInfoEntity,
      ...values,
      application: valueApplication[0],
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
          ...apiInfoEntity,
          application: apiInfoEntity?.application?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="cmspApp.apiInfo.home.createOrEditLabel" data-cy="ApiInfoCreateUpdateHeading">
            <Translate contentKey="cmspApp.apiInfo.home.createOrEditLabel">Create or edit a ApiInfo</Translate>
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
                  id="api-info-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField label={translate('cmspApp.apiInfo.name')} id="api-info-name" name="name" data-cy="name" type="text" />
              <ValidatedField label={translate('cmspApp.apiInfo.path')} id="api-info-path" name="path" data-cy="path" type="text" />
              <ValidatedField
                label={translate('cmspApp.apiInfo.requestExample')}
                id="api-info-requestExample"
                name="requestExample"
                data-cy="requestExample"
                type="textarea"
                style={{ minHeight: '200px' }}
              />
              <ValidatedField
                label={translate('cmspApp.apiInfo.responseExample')}
                id="api-info-responseExample"
                name="responseExample"
                data-cy="responseExample"
                type="textarea"
                style={{ minHeight: '200px' }}
              />
              <ValidatedField
                label={translate('cmspApp.apiInfo.dateCreate')}
                id="api-info-dateCreate"
                name="dateCreate"
                data-cy="dateCreate"
                type="date"
              />
              <div style={{ width: '100%' }}>
                <label>{translate('cmspApp.apiInfo.application')}</label>
                <br />
                <MultiSelect
                  value={valueApplication}
                  onChange={e => settvalueApplication(e.value)}
                  options={applicationList}
                  optionLabel="name"
                  filter
                  showClear
                  filterBy="name"
                  placeholder="chọn ứng dụng liên kết"
                  maxSelectedLabels={1}
                  className="w-full md:w-20rem d-flex"
                />
              </div>
              <ValidatedField
                label={translate('cmspApp.apiInfo.description')}
                id="api-info-description"
                name="description"
                data-cy="description"
                type="textarea"
                style={{ minHeight: '200px' }}
              />
              <ValidatedField label={translate('cmspApp.apiInfo.method')} id="apiInfo-method" name="method" data-cy="method" type="select">
                <option value={'GET'} key={'GET'}>
                  {'GET'}
                </option>
                <option value={'POST'} key={'POST'}>
                  {'POST'}
                </option>
                <option value={'PUT'} key={'PUT'}>
                  {'PUT'}
                </option>
                <option value={'DELETE'} key={'DELETE'}>
                  {'DELETE'}
                </option>
                <option value={'PATCH'} key={'PATCH'}>
                  {'PATCH'}
                </option>
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/api-info" replace color="info">
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

export default ApiInfoUpdate;
