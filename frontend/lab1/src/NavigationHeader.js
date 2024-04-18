import React from 'react';
import { Link } from 'react-router-dom';

const NavigationHeader = () => {
    return (
        <nav>
            <ul>
                <li><Link to="/">Home</Link></li>
                <li><Link to="/books">Books</Link></li>
                <li><Link to="/categories">Categories</Link></li>
            </ul>
        </nav>
    );
};

export default NavigationHeader;
