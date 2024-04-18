import React from 'react';
import { Categories } from './BookCategory'; // Import the enum

const CategoryList = () => {
    return (
        <div>
            <h1>Categories</h1>
            <ul>
                {Object.values(Categories).map((category, index) => (
                    <li key={index}>{category}</li>
                ))}
            </ul>
        </div>
    );
};

export default CategoryList;
