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
import { IOperaUnit } from 'app/shared/model/opera-unit.model';
import { getEntity, updateEntity, createEntity, reset } from './opera-unit.reducer';

export const OperaUnitUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const applications = useAppSelector(state => state.application.entities);
  const operaUnitEntity = useAppSelector(state => state.operaUnit.entity);
  const loading = useAppSelector(state => state.operaUnit.loading);
  const updating = useAppSelector(state => state.operaUnit.updating);
  const updateSuccess = useAppSelector(state => state.operaUnit.updateSuccess);
  const handleClose = () => {
    props.history.push('/opera-unit' + props.location.search);
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
      ...operaUnitEntity,
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
          ...operaUnitEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="cmspApp.operaUnit.home.createOrEditLabel" data-cy="OperaUnitCreateUpdateHeading">
            <Translate contentKey="cmspApp.operaUnit.home.createOrEditLabel">Create or edit a OperaUnit</Translate>
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
                  id="opera-unit-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField label={translate('cmspApp.operaUnit.name')} id="opera-unit-name" name="name" data-cy="name" type="text" />
              <ValidatedField label={translate('cmspApp.operaUnit.code')} id="opera-unit-code" name="code" data-cy="code" type="text" />
              <ValidatedField
                label={translate('cmspApp.operaUnit.address')}
                id="opera-unit-address"
                name="address"
                data-cy="address"
                type="text"
              />
              <ValidatedField label={translate('cmspApp.operaUnit.email')} id="opera-unit-email" name="email" data-cy="email" type="text" />
              <ValidatedField
                label={translate('cmspApp.operaUnit.phoneNumber')}
                id="opera-unit-phoneNumber"
                name="phoneNumber"
                data-cy="phoneNumber"
                type="text"
              />
              <ValidatedField label={translate('cmspApp.operaUnit.owner')} id="opera-unit-owner" name="owner" data-cy="owner" type="text" />
              <ValidatedField
                label={translate('cmspApp.operaUnit.description')}
                id="opera-unit-description"
                name="description"
                data-cy="description"
                type="textarea"
                style={{ minHeight: '200px' }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/opera-unit" replace color="info">
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

export default OperaUnitUpdate;
