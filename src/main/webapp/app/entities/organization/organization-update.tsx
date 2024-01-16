import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IArea } from 'app/shared/model/area.model';
import { getEntities as getAreas } from 'app/entities/area/area.reducer';
import { IOrganization } from 'app/shared/model/organization.model';
import { getEntity, updateEntity, createEntity, reset } from './organization.reducer';

export const OrganizationUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const areas = useAppSelector(state => state.area.entities);
  const organizationEntity = useAppSelector(state => state.organization.entity);
  const loading = useAppSelector(state => state.organization.loading);
  const updating = useAppSelector(state => state.organization.updating);
  const updateSuccess = useAppSelector(state => state.organization.updateSuccess);
  const handleClose = () => {
    props.history.push('/organization' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getAreas({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...organizationEntity,
      ...values,
      area: areas.find(it => it.id.toString() === values.area.toString()),
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
          ...organizationEntity,
          area: organizationEntity?.area?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="cmspApp.organization.home.createOrEditLabel" data-cy="OrganizationCreateUpdateHeading">
            <Translate contentKey="cmspApp.organization.home.createOrEditLabel">Create or edit a Organization</Translate>
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
                  id="organization-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('cmspApp.organization.name')}
                id="organization-name"
                name="name"
                data-cy="name"
                type="text"
              />
              <ValidatedField
                label={translate('cmspApp.organization.code')}
                id="organization-code"
                name="code"
                data-cy="code"
                type="text"
              />
              <ValidatedField
                label={translate('cmspApp.organization.address')}
                id="organization-address"
                name="address"
                data-cy="address"
                type="text"
              />
              <ValidatedField
                label={translate('cmspApp.organization.phoneNumber')}
                id="organization-phoneNumber"
                name="phoneNumber"
                data-cy="phoneNumber"
                type="text"
              />
              <ValidatedField
                id="organization-area"
                name="area"
                data-cy="area"
                label={translate('cmspApp.organization.area')}
                type="select"
              >
                <option value="" key="0" />
                {areas
                  ? areas.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.name}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                label={translate('cmspApp.organization.description')}
                id="organization-description"
                name="description"
                data-cy="description"
                type="textarea"
                style={{ minHeight: '200px' }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/organization" replace color="info">
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

export default OrganizationUpdate;
