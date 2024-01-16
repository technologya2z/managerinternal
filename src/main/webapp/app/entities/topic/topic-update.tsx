import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import Tooltip from '../../modules/tooltip';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IApplication } from 'app/shared/model/application.model';
import { getEntities as getApplications } from 'app/entities/application/application.reducer';
import { ITopic } from 'app/shared/model/topic.model';
import { getEntity, updateEntity, createEntity, reset } from './topic.reducer';

export const TopicUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const applications = useAppSelector(state => state.application.entities);
  const topicEntity = useAppSelector(state => state.topic.entity);
  const loading = useAppSelector(state => state.topic.loading);
  const updating = useAppSelector(state => state.topic.updating);
  const updateSuccess = useAppSelector(state => state.topic.updateSuccess);
  const handleClose = () => {
    props.history.push('/topic' + props.location.search);
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
      ...topicEntity,
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
          ...topicEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="cmspApp.topic.home.createOrEditLabel" data-cy="TopicCreateUpdateHeading">
            <Translate contentKey="cmspApp.topic.home.createOrEditLabel">Create or edit a Topic</Translate>
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
                  id="topic-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('cmspApp.topic.enviroment')}
                id="topic-enviroment"
                name="enviroment"
                data-cy="enviroment"
                type="select"
              >
                <option value={'UAT'} key={'UAT'}>
                  {'UAT'}
                </option>
                <option value={'LIVE'} key={'LIVE'}>
                  {'LIVE'}
                </option>
              </ValidatedField>
              <ValidatedField label={translate('cmspApp.topic.name')} id="topic-name" name="name" data-cy="name" type="text" />
              <ValidatedField
                label={translate('cmspApp.topic.bootrapServer')}
                id="topic-bootrapServer"
                name="bootrapServer"
                data-cy="bootrapServer"
                type="text"
              />
              <Tooltip text={translate('cmspApp.topic.tooltip.locationTopic')}>{translate('cmspApp.topic.locationTopic')}</Tooltip>
              <ValidatedField id="topic-locationTopic" name="locationTopic" data-cy="locationTopic" type="text" />
              <ValidatedField
                label={translate('cmspApp.topic.rootproducer')}
                id="topic-rootproducer"
                name="rootproducer"
                data-cy="rootproducer"
                type="text"
              />
              <ValidatedField
                label={translate('cmspApp.topic.rootTable')}
                id="topic-rootTable"
                name="rootTable"
                data-cy="rootTable"
                type="text"
              />
              <ValidatedField
                label={translate('cmspApp.topic.message')}
                id="topic-message"
                name="message"
                data-cy="message"
                type="textarea"
                style={{ minHeight: '200px' }}
              />
              <ValidatedField label={translate('cmspApp.topic.linkDoc')} id="topic-linkDoc" name="linkDoc" data-cy="linkDoc" type="text" />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/topic" replace color="info">
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

export default TopicUpdate;
