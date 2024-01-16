import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { findObjects, isEmptyObject, mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ITopic } from 'app/shared/model/topic.model';
import { getEntities as getTopics } from 'app/entities/topic/topic.reducer';
import { IApplication } from 'app/shared/model/application.model';
import { getEntities as getApplications } from 'app/entities/application/application.reducer';
import { ITopicOut } from 'app/shared/model/topic-out.model';
import { getEntity, updateEntity, createEntity, reset } from './topic-out.reducer';
import { MultiSelect } from 'primereact/multiselect';

export const TopicOutUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);
  const [valueApplication, setvalueApplication] = useState([]);
  const [valueTopic, setvalueTopic] = useState([]);

  const topics = useAppSelector(state => state.topic.entities);
  const applications = useAppSelector(state => state.application.entities);
  const topicOutEntity = useAppSelector(state => state.topicOut.entity);
  const loading = useAppSelector(state => state.topicOut.loading);
  const updating = useAppSelector(state => state.topicOut.updating);
  const updateSuccess = useAppSelector(state => state.topicOut.updateSuccess);
  const handleClose = () => {
    props.history.push('/topic-out' + props.location.search);
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
      ...topicOutEntity,
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
    if (!isEmptyObject(applications) && !isEmptyObject(topicOutEntity)) {
      if (topicOutEntity.application) {
        const arr = [];
        arr.push(topicOutEntity.application);
        setvalueApplication(findObjects(applications, arr));
      }
      if (topicOutEntity.topic) {
        const arrApi = [];
        arrApi.push(topicOutEntity.topic);
        setvalueTopic(findObjects(topics, arrApi));
      }
    }
  }, [topicOutEntity, applications]);

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...topicOutEntity,
          topic: topicOutEntity?.topic?.id,
          application: topicOutEntity?.application?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="cmspApp.topicOut.home.createOrEditLabel" data-cy="TopicOutCreateUpdateHeading">
            <Translate contentKey="cmspApp.topicOut.home.createOrEditLabel">Create or edit a TopicOut</Translate>
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
                  id="topic-out-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('cmspApp.topicOut.description')}
                id="topic-out-description"
                name="description"
                data-cy="description"
                type="text"
              />
              <ValidatedField
                label={translate('cmspApp.topicOut.dateConnect')}
                id="topic-out-dateConnect"
                name="dateConnect"
                data-cy="dateConnect"
                type="date"
              />
              <div style={{ width: '100%' }}>
                <label>{translate('cmspApp.topicOut.topic')}</label>
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
                <label className='margin' >{translate('cmspApp.topicOut.application')}</label>
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
              <Button tag={Link} id="cancel-save" className='margin' data-cy="entityCreateCancelButton" to="/topic-out" replace color="info">
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

export default TopicOutUpdate;
