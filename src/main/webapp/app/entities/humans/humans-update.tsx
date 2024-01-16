import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { getEntities as getJobtitles } from 'app/entities/jobtitle/jobtitle.reducer';
import { getEntities as getDepartments } from 'app/entities/department/department.reducer';
import { getEntity, updateEntity, createEntity, reset } from './humans.reducer';
import { getEntities as getApplications } from 'app/entities/application/application.reducer';
import {findObjects, mapIdList} from "app/shared/util/entity-utils";

export const HumansUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const jobtitleList = useAppSelector(state => state.jobtitle.entities);
  const departmentList = useAppSelector(state => state.department.entities);
  const humansEntity = useAppSelector(state => state.humans.entity);
  const loading = useAppSelector(state => state.humans.loading);
  const updating = useAppSelector(state => state.humans.updating);
  const updateSuccess = useAppSelector(state => state.humans.updateSuccess);
  const applicationList = useAppSelector(state => state.application.entities);
  const handleClose = () => {
    props.history.push('/humans' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }
    dispatch(getJobtitles({page:0,size:2000,sort:'id,desc'}));
    dispatch(getDepartments({page:0,size:2000,sort:'id,desc'}));
    dispatch(getApplications({page:0,size:2000,sort:'id,desc'}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...humansEntity,
      ...values,
      department: departmentList.find(it => it.id.toString() === values.department.toString()),
      applications: mapIdList(values.applications),
      jobtitles: mapIdList(values.jobtitles)
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
          ...humansEntity,
          jobtitles: humansEntity?.jobtitles?.map(e => e.id.toString()),
          applications: humansEntity?.applications?.map(e => e.id.toString()),
          department: humansEntity?.department?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="cmspApp.humans.home.createOrEditLabel" data-cy="HumansCreateUpdateHeading">
            <Translate contentKey="cmspApp.humans.home.createOrEditLabel">Create or edit a Humans</Translate>
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
                  id="humans-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('cmspApp.humans.fullName')}
                id="humans-fullName"
                name="fullName"
                data-cy="fullName"
                type="text"
              />
              <ValidatedField label={translate('cmspApp.humans.code')} id="humans-code" name="code" data-cy="code" type="text" />
              <ValidatedField
                label={translate('cmspApp.humans.userName')}
                id="humans-userName"
                name="userName"
                data-cy="userName"
                type="text"
              />
              <ValidatedField label={translate('cmspApp.humans.email')} id="humans-email" name="email" data-cy="email" type="text" />
              <ValidatedField label={translate('cmspApp.humans.active')} id="humans-active" name="active" data-cy="active" type="select" >
                <option value="" key="0" />
                <option value="work">hoạt động</option>
                <option value="quit">đã nghỉ</option>
              {/* <option value={'1'} key={'Bật'}>
                  {'Hoạt động'}
                </option>
                <option value={'0'} key={'tắt'}>
                  {'Không hoạt động'}
                </option> */}
              </ValidatedField>

              <ValidatedField
                label={translate('cmspApp.humans.phoneNumber')}
                id="humans-phoneNumber"
                name="phoneNumber"
                data-cy="phoneNumber"
                type="text"
              />
              <ValidatedField
                label={translate('cmspApp.humans.dateOfBirth')}
                id="humans-dateOfBirth"
                name="dateOfBirth"
                data-cy="dateOfBirth"
                type="date"
              />
              <ValidatedField
                label={translate('cmspApp.humans.homePhome')}
                id="humans-homePhome"
                name="homePhome"
                data-cy="homePhome"
                type="text"
              />
              <ValidatedField
                label={translate('cmspApp.humans.address')}
                id="humans-address"
                name="address"
                data-cy="address"
                type="text"
              />
              <ValidatedField label={translate('cmspApp.humans.wards')} id="humans-wards" name="wards" data-cy="wards" type="text" />
              <ValidatedField
                label={translate('cmspApp.humans.district')}
                id="humans-district"
                name="district"
                data-cy="district"
                type="text"
              />
              <ValidatedField
                label={translate('cmspApp.humans.province')}
                id="humans-province"
                name="province"
                data-cy="province"
                type="text"
              />
              <ValidatedField
                label={translate('cmspApp.humans.joinDate')}
                id="humans-joinDate"
                name="joinDate"
                data-cy="joinDate"
                type="date"
              />
              <ValidatedField
                label={translate('cmspApp.humans.married')}
                id="humans-married"
                data-cy="married"
                type="select"
                name="married"
              >
                <option value={'Đã kết hôn'} key={'Đã kết hôn'}>
                  {'Đã kết hôn'}
                </option>
                <option value={'Độc thân'} key={'Độc thân'}>
                  {'Độc thân'}
                </option>
              </ValidatedField>
              <ValidatedField label={translate('cmspApp.humans.gender')} id="humans-gender" name="gender" data-cy="gender" type="select">
                <option value={'nam'} key={'nam'}>
                  {'nam'}
                </option>
                <option value={'nữ'} key={'nữ'}>
                  {'nữ'}
                </option>
              </ValidatedField>

              <ValidatedField
                label={translate('cmspApp.humans.jobtitle')}
                id="humans-jobtitles"
                data-cy="jobtitles"
                type="select"
                name="jobtitles"
              >
                <option value="" key="0" />
                {jobtitleList
                  ? jobtitleList.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.name}
                      </option>
                    ))
                  : null}
              </ValidatedField>

              <ValidatedField
                label='Khối'
                id="humans-humanType"
                data-cy="humanType"
                type="select"
                name="humanType"
              >
                <option value={'CNTT'} key={'CNTT'}>
                  {'Công nghệ thông tin'}
                </option>
                <option value={'KHCN'} key={'KHCN'}>
                  {'Khách hàng cá nhân'}
                </option>
                <option value={'VPQLDA'} key={'VPQLDA'}>
                  {'Văn phòng quản lý dự án'}
                </option>
                <option value={'PARTNER'} key={'PARTNER'}>
                  {'Đối tác'}
                </option>

              </ValidatedField>

              <ValidatedField
                id="humans-department"
                name="department"
                data-cy="department"
                label={translate('cmspApp.humans.department')}
                type="select"
              >
                <option value="" key="0" />
                {departmentList
                  ? departmentList.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.name}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="humans-applications"
                name="applications"
                data-cy="applications"
                label={translate('cmspApp.humans.application')}
                type="select"
                multiple={true}
              >
                <option value="" key="0" />
                {applicationList
                  ? applicationList.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.name}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                label={translate('cmspApp.humans.description')}
                id="humans-description"
                name="description"
                data-cy="description"
                style={{ minHeight: '200px' }}
                type="textarea"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/humans" replace color="info">
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

export default HumansUpdate;
