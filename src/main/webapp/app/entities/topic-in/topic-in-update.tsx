import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, { useEffect, useState } from 'react';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';

import { useAppDispatch, useAppSelector } from 'app/config/store';
import { findObjects, isEmptyObject } from 'app/shared/util/entity-utils';

import { getEntities as getApplications } from 'app/entities/application/application.reducer';
import { getEntities as getTopics } from 'app/entities/topic/topic.reducer';
import { MultiSelect } from 'primereact/multiselect';
import { createEntity, getEntity, reset, updateEntity } from './topic-in.reducer';

export const TopicInUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const [valueApplication, setvalueApplication] = useState([]);
  const [valueTopic, setvalueTopic] = useState([]);

  const topics = useAppSelector(state => state.topic.entities);
  const applications = useAppSelector(state => state.application.entities);
  const topicInEntity = useAppSelector(state => state.topicIn.entity);
  const loading = useAppSelector(state => state.topicIn.loading);
  const updating = useAppSelector(state => state.topicIn.updating);
  const updateSuccess = useAppSelector(state => state.topicIn.updateSuccess);
  const handleClose = () => {
    props.history.push('/topic-in' + props.location.search);
  };

  useEffect(() => {
    dispatch(getTopics({ page: 0, size: 1000, sort: 'id,asc' }));
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

  const saveEntity = values => {
    const entity = {
      ...topicInEntity,
      ...values,
      topic: valueTopic ? valueTopic[0] : null,
      application: valueApplication ? valueApplication[0] : null,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  useEffect(() => {
    if (!isEmptyObject(applications) && !isEmptyObject(topicInEntity)) {
      if (topicInEntity.application) {
        const arr = [];
        arr.push(topicInEntity.application);
        setvalueApplication(findObjects(applications, arr));
      }
      if (topicInEntity.topic) {
        const arrApi = [];
        arrApi.push(topicInEntity.topic);
        setvalueTopic(findObjects(topics, arrApi));
      }
    }
  }, [topicInEntity, applications]);

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...topicInEntity,
          topic: topicInEntity?.topic?.id,
          application: topicInEntity?.application?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="cmspApp.topicIn.home.createOrEditLabel" data-cy="TopicInCreateUpdateHeading">
            <Translate contentKey="cmspApp.topicIn.home.createOrEditLabel">Create or edit a TopicIn</Translate>
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
                  id="topic-in-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('cmspApp.topicIn.dateConnect')}
                id="topic-in-dateConnect"
                name="dateConnect"
                data-cy="dateConnect"
                type="date"
              />
              <ValidatedField
                label={translate('cmspApp.topicIn.description')}
                id="topic-in-description"
                name="description"
                data-cy="description"
                type="text"
              />
              <div style={{ width: '100%' }}>
                <label>{translate('cmspApp.topicIn.topic')}</label>
                <br />
                <MultiSelect
                  value={valueTopic}
                  onChange={e => setvalueTopic(e.value)}
                  options={topics}
                  optionLabel="name"
                  filter
                  showClear
                  filterBy="name"
                  placeholder="chọn topic liên kết"
                  maxSelectedLabels={1}
                  className="w-full md:w-20rem d-flex"
                />
              </div>
              <div style={{ width: '100%' }}>
                <label className='margin'>{translate('cmspApp.topicIn.application')}</label>
                <br />
                <MultiSelect
                  value={valueApplication}
                  onChange={e => setvalueApplication(e.value)}
                  options={applications}
                  optionLabel="name"
                  filter
                  showClear
                  filterBy="name"
                  placeholder="chọn application liên kết"
                  maxSelectedLabels={1}
                  className="w-full md:w-20rem d-flex"
                />
              </div>
              <Button tag={Link} id="cancel-save" className='margin' data-cy="entityCreateCancelButton" to="/topic-in" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" className='margin' data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
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

export default TopicInUpdate;
