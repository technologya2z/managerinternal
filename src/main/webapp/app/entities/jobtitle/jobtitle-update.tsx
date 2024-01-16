import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IHumans } from 'app/shared/model/humans.model';
import { getEntities as getHumans } from 'app/entities/humans/humans.reducer';
import { IJobtitle } from 'app/shared/model/jobtitle.model';
import { getEntity, updateEntity, createEntity, reset } from './jobtitle.reducer';

export const JobtitleUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const humans = useAppSelector(state => state.humans.entities);
  const jobtitleEntity = useAppSelector(state => state.jobtitle.entity);
  const loading = useAppSelector(state => state.jobtitle.loading);
  const updating = useAppSelector(state => state.jobtitle.updating);
  const updateSuccess = useAppSelector(state => state.jobtitle.updateSuccess);
  const handleClose = () => {
    props.history.push('/jobtitle' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getHumans({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...jobtitleEntity,
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
          ...jobtitleEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="cmspApp.jobtitle.home.createOrEditLabel" data-cy="JobtitleCreateUpdateHeading">
            <Translate contentKey="cmspApp.jobtitle.home.createOrEditLabel">Create or edit a Jobtitle</Translate>
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
                  id="jobtitle-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField label={translate('cmspApp.jobtitle.name')} id="jobtitle-name" name="name" data-cy="name" type="text" />
              <ValidatedField label={translate('cmspApp.jobtitle.code')} id="jobtitle-code" name="code" data-cy="code" type="text" />
              <ValidatedField
                label={translate('cmspApp.jobtitle.description')}
                id="jobtitle-description"
                name="description"
                data-cy="description"
                type="textarea"
                style={{ minHeight: '200px' }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/jobtitle" replace color="info">
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

export default JobtitleUpdate;
