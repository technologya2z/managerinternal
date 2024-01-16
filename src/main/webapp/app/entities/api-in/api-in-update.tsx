import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, { useEffect, useState } from 'react';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';

import { useAppDispatch, useAppSelector } from 'app/config/store';
import { findObjects, isEmptyObject } from 'app/shared/util/entity-utils';

import { getEntities as getApiInfos } from 'app/entities/api-info/api-info.reducer';
import { getEntities as getApplications } from 'app/entities/application/application.reducer';
import { MultiSelect } from 'primereact/multiselect';
import { createEntity, getEntity, reset, updateEntity } from './api-in.reducer';

export const ApiInUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);
  const [valueApplication, setvalueApplication] = useState([]);
  const [valueApiIn, setvalueApiIn] = useState([]);

  const apiInfos = useAppSelector(state => state.apiInfo.entities);
  const applications = useAppSelector(state => state.application.entities);
  const apiInEntity = useAppSelector(state => state.apiIn.entity);
  const loading = useAppSelector(state => state.apiIn.loading);
  const updating = useAppSelector(state => state.apiIn.updating);
  const updateSuccess = useAppSelector(state => state.apiIn.updateSuccess);
  const handleClose = () => {
    props.history.push('/api-in' + props.location.search);
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
    if (!isEmptyObject(applications) && !isEmptyObject(apiInEntity)) {
      if (apiInEntity.application) {
        const arr = [];
        arr.push(apiInEntity.application);
        setvalueApplication(findObjects(applications, arr));
      }
      if (apiInEntity.apiInfo) {
        const arrApi = [];
        arrApi.push(apiInEntity.apiInfo);
        setvalueApiIn(findObjects(apiInfos, arrApi));
      }
    }
  }, [apiInEntity, applications]);

  const saveEntity = values => {
    const entity = {
      ...apiInEntity,
      ...values,
      apiInfo: valueApiIn[0],
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
          ...apiInEntity,
          apiInfo: apiInEntity?.apiInfo?.id,
          application: apiInEntity?.application?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="cmspApp.apiIn.home.createOrEditLabel" data-cy="ApiInCreateUpdateHeading">
            <Translate contentKey="cmspApp.apiIn.home.createOrEditLabel">Create or edit a ApiIn</Translate>
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
                  id="api-in-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('cmspApp.apiIn.dateConnect')}
                id="api-in-dateConnect"
                name="dateConnect"
                data-cy="dateConnect"
                type="date"
              />
              <div style={{ width: '100%' }}>
                <label >{translate('cmspApp.apiIn.application')}</label>
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
                  className="w-full md:w-20rem d-flex "
                />
              </div>
              <div style={{ width: '100%' }}>
                <label>{translate('cmspApp.apiIn.apiInfo')}</label>
                <br />
                <MultiSelect
                  value={valueApiIn}
                  onChange={e => setvalueApiIn(e.value)}
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
                label={translate('cmspApp.apiIn.description')}
                id="api-in-description"
                name="description"
                data-cy="description"
                type="textarea"
                style={{ minHeight: '200px' }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/api-in" replace color="info">
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

export default ApiInUpdate;
