import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';
import { NavDropdown } from 'app/shared/layout/menus/menu-components';
const MenuTopic = () => {
  return (
    <NavDropdown icon="th-list" name={'Topic'} id="entity-menu" data-cy="entity" style={{ maxHeight: '80vh', overflow: 'auto' }}>
      <>
        <MenuItem icon="asterisk" to="/topic">
         <span className="fontNav">
            <Translate contentKey="global.menu.entities.topic" />
         </span>
        </MenuItem>

        <MenuItem icon="asterisk" to="/topic-in">
        <span className="fontNav">
            <Translate contentKey="global.menu.entities.topicIn" />
        </span>
        </MenuItem>

        <MenuItem icon="asterisk" to="/topic-out">
          <span className="fontNav">
            <Translate contentKey="global.menu.entities.topicOut" />
          </span>
        </MenuItem>


        {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
      </>
    </NavDropdown>
  );
};

export default MenuTopic as React.ComponentType<any>;
