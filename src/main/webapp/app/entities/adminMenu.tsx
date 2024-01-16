import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';
import { NavDropdown } from 'app/shared/layout/menus/menu-components';
const AdminMenu = () => {
  return (
    <NavDropdown icon="th-list" name={'Quản trị'} id="entity-menu" data-cy="entity" style={{ maxHeight: '80vh', overflow: 'auto' }}>
      <>
        <MenuItem icon="database" to="/database-info">
         <span className="fontNav">
            <Translate contentKey="global.menu.entities.databaseInfo" />
         </span>
        </MenuItem>
        <MenuItem icon="database" to="/opera-unit">
          <span className="fontNav">
            <Translate contentKey="global.menu.entities.operaUnit" />
          </span>
        </MenuItem>


        {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
      </>
    </NavDropdown>
  );
};

export default AdminMenu as React.ComponentType<any>;
