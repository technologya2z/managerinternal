import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './partner-1.reducer';

export const Partner1Detail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const partner1Entity = useAppSelector(state => state.partner1.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="partner1DetailsHeading">Partner1</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{partner1Entity.id}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{partner1Entity.name}</dd>
          {/* <dt>
            <span id="code">Code</span>
          </dt> */}
          <dd>{partner1Entity.code ? <TextFormat value={partner1Entity.code} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{partner1Entity.description}</dd>
        </dl>
        <Button tag={Link} to="/partner-1" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/partner-1/${partner1Entity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default Partner1Detail;
