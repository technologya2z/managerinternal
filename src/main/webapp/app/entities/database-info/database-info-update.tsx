import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IApplication } from 'app/shared/model/application.model';
import { getEntities as getApplications } from 'app/entities/application/application.reducer';
import { IDatabaseInfo } from 'app/shared/model/database-info.model';
import { DatabaseType } from 'app/shared/model/enumerations/database-type.model';
import { getEntity, updateEntity, createEntity, reset } from './database-info.reducer';

export const DatabaseInfoUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const applications = useAppSelector(state => state.application.entities);
  const databaseInfoEntity = useAppSelector(state => state.databaseInfo.entity);
  const loading = useAppSelector(state => state.databaseInfo.loading);
  const updating = useAppSelector(state => state.databaseInfo.updating);
  const updateSuccess = useAppSelector(state => state.databaseInfo.updateSuccess);
  const databaseTypeValues = Object.keys(DatabaseType);
  const handleClose = () => {
    props.history.push('/database-info' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getApplications({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...databaseInfoEntity,
      ...values,
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
          dataType: 'Oracle',
          ...databaseInfoEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="cmspApp.databaseInfo.home.createOrEditLabel" data-cy="DatabaseInfoCreateUpdateHeading">
            <Translate contentKey="cmspApp.databaseInfo.home.createOrEditLabel">Create or edit a DatabaseInfo</Translate>
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
                  id="database-info-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('cmspApp.databaseInfo.name')}
                id="database-info-name"
                name="name"
                data-cy="name"
                type="text"
              />
              <ValidatedField
                label={translate('cmspApp.databaseInfo.serviceName')}
                id="database-info-serviceName"
                name="serviceName"
                data-cy="serviceName"
                type="text"
              />
              <ValidatedField
                label={translate('cmspApp.databaseInfo.userName')}
                id="database-info-userName"
                name="userName"
                data-cy="userName"
                type="text"
              />
              <ValidatedField
                label={translate('cmspApp.databaseInfo.ipServer')}
                id="database-info-ipServer"
                name="ipServer"
                data-cy="ipServer"
                type="text"
              />
              <ValidatedField
                label={translate('cmspApp.databaseInfo.port')}
                id="database-info-port"
                name="port"
                data-cy="port"
                type="text"
              />
              <ValidatedField
                label={translate('cmspApp.databaseInfo.dataType')}
                id="database-info-dataType"
                name="dataType"
                data-cy="dataType"
                type="select"
              >
                {databaseTypeValues.map(databaseType => (
                  <option value={databaseType} key={databaseType}>
                    {translate('cmspApp.DatabaseType.' + databaseType)}
                  </option>
                ))}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/database-info" replace color="info">
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

export default DatabaseInfoUpdate;
