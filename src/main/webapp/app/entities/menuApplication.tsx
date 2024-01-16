import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';
import { faMap } from '@fortawesome/free-solid-svg-icons/faMap';
import { faBan } from '@fortawesome/free-solid-svg-icons/faBan';
import { NavDropdown } from 'app/shared/layout/menus/menu-components';
import EntitiesMenuItems from 'app/entities/menu';

const ApplicationMenu = () => {
  return (
    <NavDropdown icon="th-list" name={'Ứng dụng'} id="entity-menu" data-cy="entity" style={{ maxHeight: '80vh', overflow: 'auto' }}>
      <>
        {/* prettier-ignore */}
        <MenuItem icon="robot" to="/application">
         <span className="fontNav">
            <Translate contentKey="global.menu.entities.application" />
         </span>
        </MenuItem>
        <MenuItem icon="info-circle" to="/api-info">
          <span className="fontNav">
            <Translate contentKey="global.menu.entities.apiInfo" />
          </span>
        </MenuItem>
        <MenuItem icon="download" to="/api-in">
         <span className="fontNav">
            Ứng dụng → API
         </span>
        </MenuItem>
        <MenuItem icon="upload" to="/api-out">
         <span className="fontNav">
            API → Ứng dụng
         </span>
        </MenuItem>
        {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
      </>
    </NavDropdown>
  );
};

export default ApplicationMenu as React.ComponentType<any>;
