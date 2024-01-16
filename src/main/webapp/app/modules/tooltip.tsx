import React, { useState } from 'react';
import PropTypes from 'prop-types';

const Tooltip = ({ text, children }) => {
  const [showTooltip, setShowTooltip] = useState(false);

  const handleMouseEnter = () => setShowTooltip(true);
  const handleMouseLeave = () => setShowTooltip(false);

  return (
    <div style={{ position: 'relative' }}>
      <div onMouseEnter={handleMouseEnter} onMouseLeave={handleMouseLeave}>
        {children}
      </div>
      {showTooltip && (
        <div
          style={{
            position: 'absolute',
            bottom: '100%',
            left: '10%',
            transform: 'translateX(-100%)',
            padding: '0.5rem',
            borderRadius: '0.25rem',
            backgroundColor: 'rgba(0, 0, 0, 0.75)',
            color: '#fff',
            fontSize: '0.875rem',
          }}
        >
          {text}
        </div>
      )}
    </div>
  );
};

Tooltip.propTypes = {
  text: PropTypes.string.isRequired,
  children: PropTypes.node.isRequired,
};

export default Tooltip;
