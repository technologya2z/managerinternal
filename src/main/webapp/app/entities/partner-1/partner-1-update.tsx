import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IPartner1 } from 'app/shared/model/partner-1.model';
import { getEntity, updateEntity, createEntity, reset } from './partner-1.reducer';

export const Partner1Update = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const partner1Entity = useAppSelector(state => state.partner1.entity);
  const loading = useAppSelector(state => state.partner1.loading);
  const updating = useAppSelector(state => state.partner1.updating);
  const updateSuccess = useAppSelector(state => state.partner1.updateSuccess);
  const handleClose = () => {
    props.history.push('/partner-1' + props.location.search);
  };

  useEffect(() => {
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

  const saveEntity = values => {
    values.code = convertDateTimeToServer(values.code);

    const entity = {
      ...partner1Entity,
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
      ? {
          code: displayDefaultDateTime(),
        }
      : {
          ...partner1Entity,
          code: convertDateTimeFromServer(partner1Entity.code),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="newApp.partner1.home.createOrEditLabel" data-cy="Partner1CreateUpdateHeading">
            Create or edit a Partner1
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="partner-1-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField
                label="Name"
                id="partner-1-name"
                name="name"
                data-cy="name"
                type="text"
                validate={{
                  maxLength: { value: 100, message: 'This field cannot be longer than 100 characters.' },
                }}
              />
              {/* <ValidatedField
                label="Code"
                id="partner-1-code"
                name="code"
                data-cy="code"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              /> */}
              <ValidatedField
                label="Description"
                id="partner-1-description"
                name="description"
                data-cy="description"
                type="text"
                validate={{
                  maxLength: { value: 50, message: 'This field cannot be longer than 50 characters.' },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/partner-1" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default Partner1Update;
