import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IAccessLog } from 'app/shared/model/access-log.model';
import { getEntity, updateEntity, createEntity, reset } from './access-log.reducer';

export const AccessLogUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const accessLogEntity = useAppSelector(state => state.accessLog.entity);
  const loading = useAppSelector(state => state.accessLog.loading);
  const updating = useAppSelector(state => state.accessLog.updating);
  const updateSuccess = useAppSelector(state => state.accessLog.updateSuccess);
  const handleClose = () => {
    props.history.push('/access-log' + props.location.search);
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
    values.accessTime = convertDateTimeToServer(values.accessTime);

    const entity = {
      ...accessLogEntity,
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
          accessTime: displayDefaultDateTime(),
        }
      : {
          ...accessLogEntity,
          accessTime: convertDateTimeFromServer(accessLogEntity.accessTime),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="newApp.accessLog.home.createOrEditLabel" data-cy="AccessLogCreateUpdateHeading">
            Create or edit a AccessLog
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="access-log-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField
                label="Emp Code"
                id="access-log-empCode"
                name="empCode"
                data-cy="empCode"
                type="text"
                validate={{
                  maxLength: { value: 50, message: 'This field cannot be longer than 50 characters.' },
                }}
              />
              <ValidatedField
                label="Emp Username"
                id="access-log-empUsername"
                name="empUsername"
                data-cy="empUsername"
                type="text"
                validate={{
                  maxLength: { value: 50, message: 'This field cannot be longer than 50 characters.' },
                }}
              />
              <ValidatedField
                label="Emp Full Name"
                id="access-log-empFullName"
                name="empFullName"
                data-cy="empFullName"
                type="text"
                validate={{
                  maxLength: { value: 50, message: 'This field cannot be longer than 50 characters.' },
                }}
              />
              <ValidatedField
                label="Access Resource"
                id="access-log-accessResource"
                name="accessResource"
                data-cy="accessResource"
                type="text"
                validate={{
                  maxLength: { value: 100, message: 'This field cannot be longer than 100 characters.' },
                }}
              />
              <ValidatedField label="Description" id="access-log-description" name="description" data-cy="description" type="text" />
              <ValidatedField label="Ip Address" id="access-log-ipAddress" name="ipAddress" data-cy="ipAddress" type="text" />
              <ValidatedField
                label="Access Time"
                id="access-log-accessTime"
                name="accessTime"
                data-cy="accessTime"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/access-log" replace color="info">
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

export default AccessLogUpdate;
