import React, { useEffect, useState } from 'react';
import { Alert, Button, Col, Form, Modal, ModalBody, ModalFooter, ModalHeader, Row, Table } from 'reactstrap';
import { getSortState, TextFormat, translate, Translate, ValidatedField } from 'react-jhipster';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { ASC, DESC, ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

import { searchEntitiesTopicIn } from 'app/entities/topic-in/topic-in.reducer';
import { searchEntitiesTopicOut} from 'app/entities/topic-out/topic-out.reducer';

const ModalTopic = ({ props, isOpen, onClose, idTopic }) => {
  const dispatch = useAppDispatch();
  const [modalIsOpen, setModalIsOpen] = useState(isOpen);
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  const topicInList = useAppSelector(state => state.topicIn.entities);
  const topicOutList = useAppSelector(state => state.topicOut.entities);

  const handleClose = () => {
    setModalIsOpen(false);
    onClose && onClose();
  };

  useEffect(() => {
    if (idTopic != null) {
      const searchCriterials = [];
      searchCriterials['TopicId.equals'] = idTopic;
      dispatch(
        searchEntitiesTopicIn({
          page: paginationState.activePage - 1,
          size: paginationState.itemsPerPage,
          sort: `${paginationState.sort},${paginationState.order}`,
          searchCriterials,
        })
      );

      dispatch(
        searchEntitiesTopicOut({
          page: paginationState.activePage - 1,
          size: paginationState.itemsPerPage,
          sort: `${paginationState.sort},${paginationState.order}`,
          searchCriterials,
        })
      );
    }
  }, [idTopic]);

  return (
    <div>
      {isOpen || modalIsOpen ? (
        <Modal size={'lg'} isOpen={true} toggle={handleClose} autoFocus={false} backdrop="static" id="modal-api-page">
          <ModalHeader id="login-title" data-cy="loginTitle" toggle={handleClose}>
            <label>Thông tin Topic</label>
          </ModalHeader>
          <ModalBody>
            <Row>
              <h4>
                <Translate contentKey="cmspApp.topicIn.home.title">TOPIC In</Translate>
              </h4>
              <div className="table-responsive">
                {topicInList && topicInList.length > 0 ? (
                  <Table responsive>
                    <thead>
                      <tr>
                        <th>
                          <label>STT</label>
                        </th>
                        <th>
                          <Translate contentKey="cmspApp.topicIn.topic">Topic</Translate> <FontAwesomeIcon icon="sort" />
                        </th>
                        <th>
                          <Translate contentKey="cmspApp.topicIn.application">Application</Translate> <FontAwesomeIcon icon="sort" />
                        </th>
                        <th className="hand">
                          <Translate contentKey="cmspApp.topicIn.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                        </th>
                        <th className="hand">
                          <Translate contentKey="cmspApp.topicIn.dateConnect">Date Connect</Translate> <FontAwesomeIcon icon="sort" />
                        </th>

                        <th />
                      </tr>
                    </thead>
                    <tbody>
                      {topicInList.map((topicIn, i) => (
                        <tr key={`entity-${i}`} data-cy="entityTable">
                          <td>{i + 1}</td>
                          <td>{topicIn.topic ? <Link to={`/topic/${topicIn.topic.id}`}>{topicIn.topic.name}</Link> : ''}</td>
                          <td>
                            {topicIn.application ? (
                              <Link to={`/application/${topicIn.application.id}`}>{topicIn.application.name}</Link>
                            ) : (
                              ''
                            )}
                          </td>
                          <td>{topicIn.description}</td>
                          <td>
                            {topicIn.dateConnect ? (
                              <TextFormat type="date" value={topicIn.dateConnect} format={APP_LOCAL_DATE_FORMAT} />
                            ) : null}
                          </td>
                          <td className="text-end">
                            <div className="btn-group flex-btn-group-container">
                              <Button tag={Link} to={`/topic-in/${topicIn.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                                <FontAwesomeIcon icon="eye" />{' '}
                                <span className="d-none d-md-inline">
                                  <Translate contentKey="entity.action.view">View</Translate>
                                </span>
                              </Button>
                            </div>
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </Table>
                ) : (
                  <div className="alert alert-warning">
                    <Translate contentKey="cmspApp.topicIn.home.notFound">No Topic Ins found</Translate>
                  </div>
                )}
              </div>
            </Row>

            <Row>
              <h4>
                <Translate contentKey="cmspApp.topicOut.home.title">TOPIC In</Translate>
              </h4>
              <div className="table-responsive">
                {topicOutList && topicOutList.length > 0 ? (
                  <Table responsive>
                    <thead>
                      <tr>
                        <th>
                          <label>STT</label>
                        </th>
                        <th>
                          <Translate contentKey="cmspApp.topicIn.topic">Topic</Translate> <FontAwesomeIcon icon="sort" />
                        </th>
                        <th>
                          <Translate contentKey="cmspApp.topicIn.application">Application</Translate> <FontAwesomeIcon icon="sort" />
                        </th>
                        <th className="hand">
                          <Translate contentKey="cmspApp.topicIn.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                        </th>
                        <th className="hand">
                          <Translate contentKey="cmspApp.topicIn.dateConnect">Date Connect</Translate> <FontAwesomeIcon icon="sort" />
                        </th>

                        <th />
                      </tr>
                    </thead>
                    <tbody>
                      {topicOutList.map((topicIn, i) => (
                        <tr key={`entity-${i}`} data-cy="entityTable">
                          <td>{i + 1}</td>
                          <td>{topicIn.topic ? <Link to={`/topic/${topicIn.topic.id}`}>{topicIn.topic.name}</Link> : ''}</td>
                          <td>
                            {topicIn.application ? (
                              <Link to={`/application/${topicIn.application.id}`}>{topicIn.application.name}</Link>
                            ) : (
                              ''
                            )}
                          </td>
                          <td>{topicIn.description}</td>
                          <td>
                            {topicIn.dateConnect ? (
                              <TextFormat type="date" value={topicIn.dateConnect} format={APP_LOCAL_DATE_FORMAT} />
                            ) : null}
                          </td>
                          <td className="text-end">
                            <div className="btn-group flex-btn-group-container">
                              <Button tag={Link} to={`/topic-in/${topicIn.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                                <FontAwesomeIcon icon="eye" />{' '}
                                <span className="d-none d-md-inline">
                                  <Translate contentKey="entity.action.view">View</Translate>
                                </span>
                              </Button>
                            </div>
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </Table>
                ) : (
                  <div className="alert alert-warning">
                    <Translate contentKey="cmspApp.topicIn.home.notFound">No Topic Ins found</Translate>
                  </div>
                )}
              </div>
            </Row>
          </ModalBody>
        </Modal>
      ) : null}
    </div>
  );
};

export default ModalTopic;
