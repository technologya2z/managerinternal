import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { findObjects, isEmptyObject, mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IApiInfo } from 'app/shared/model/api-info.model';
import { getEntities as getApiInfos } from 'app/entities/api-info/api-info.reducer';
import { IApplication } from 'app/shared/model/application.model';
import { getEntities as getApplications } from 'app/entities/application/application.reducer';
import { IApiOut } from 'app/shared/model/api-out.model';
import { getEntity, updateEntity, createEntity, reset } from './api-out.reducer';
import { MultiSelect } from 'primereact/multiselect';

export const ApiOutUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);
  const [valueApplication, setvalueApplication] = useState([]);
  const [valueApiOut, setvalueApiOut] = useState([]);

  const apiInfos = useAppSelector(state => state.apiInfo.entities);
  const applications = useAppSelector(state => state.application.entities);
  const apiOutEntity = useAppSelector(state => state.apiOut.entity);
  const loading = useAppSelector(state => state.apiOut.loading);
  const updating = useAppSelector(state => state.apiOut.updating);
  const updateSuccess = useAppSelector(state => state.apiOut.updateSuccess);
  const handleClose = () => {
    props.history.push('/api-out' + props.location.search);
  };

  useEffect(() => {
    dispatch(getApiInfos({ page: 0, size: 1000, sort: 'id,asc' }));
    dispatch(getApplications({ page: 0, size: 1000, sort: 'id,asc' }));
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  useEffect(() => {
    if (!isEmptyObject(applications) && !isEmptyObject(apiOutEntity)) {
      if (apiOutEntity.application) {
        const arr = [];
        arr.push(apiOutEntity.application);
        setvalueApplication(findObjects(applications, arr));
      }

      if (apiOutEntity.apiInfo) {
        const arrApi = [];
        arrApi.push(apiOutEntity.apiInfo);
        setvalueApiOut(findObjects(apiInfos, arrApi));
      }
    }
  }, [apiOutEntity, applications]);

  const saveEntity = values => {
    const entity = {
      ...apiOutEntity,
      ...values,
      apiInfo: valueApiOut ? valueApiOut[0] : null,
      application: valueApplication ? valueApplication[0] : null,
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
          ...apiOutEntity,
          apiInfo: apiOutEntity?.apiInfo?.id,
          application: apiOutEntity?.application?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="cmspApp.apiOut.home.createOrEditLabel" data-cy="ApiOutCreateUpdateHeading">
            <Translate contentKey="cmspApp.apiOut.home.createOrEditLabel">Create or edit a ApiOut</Translate>
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
                  id="api-out-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('cmspApp.apiOut.dateConnect')}
                id="api-out-dateConnect"
                name="dateConnect"
                data-cy="dateConnect"
                type="date"
              />
              <div style={{ width: '100%' }}>
                <label>{translate('cmspApp.apiOut.application')}</label>
                <br />
                <MultiSelect
                  value={valueApplication}
                  onChange={e => setvalueApplication(e.value)}
                  options={applications}
                  optionLabel="name"
                  filter
                  showClear
                  filterBy="name"
                  placeholder="chọn ứng dụng liên kết"
                  maxSelectedLabels={1}
                  className="w-full md:w-20rem d-flex"
                />
              </div>
              <div style={{ width: '100%' }}>
                <label>{translate('cmspApp.apiOut.apiInfo')}</label>
                <br />
                <MultiSelect
                  value={valueApiOut}
                  onChange={e => setvalueApiOut(e.value)}
                  options={apiInfos}
                  optionLabel="name"
                  filter
                  showClear
                  filterBy="name"
                  placeholder="chọn api liên kết"
                  maxSelectedLabels={1}
                  className="w-full md:w-20rem d-flex"
                />
              </div>
              <ValidatedField
                label={translate('cmspApp.apiOut.description')}
                id="api-out-description"
                name="description"
                data-cy="description"
                type="textarea"
                style={{ minHeight: '200px' }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/api-out" replace color="info">
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

export default ApiOutUpdate;
