import React, {useState, useEffect} from 'react';
import {redirect, useNavigate, useParams} from 'react-router-dom';
import axios from "axios";
import {Categories} from "./BookCategory";

const BookEdit = () => {
    const navigate = useNavigate()
    const {id: idString} = useParams();
    const id = +idString;

    const [formData, setFormData] = useState({
        name: '',
        category: 'THRILLER',
        authorId: 1,
        availableCopies: '',
    });

    const [authors, setAuthors] = useState([])

    const fetchAuthors = async () => {
        try {
            const response = await axios.get('/api/authors')
            setAuthors(response.data)
        } catch (error) {
            console.error('Error fetching authors', error)
        }
    }

    const fetchBook = async () => {
        if (!id) {
            return;
        }
        try {
            const response = await axios.get(`/api/books/${id}`);
            setFormData({
                name: response.data.name,
                category: response.data.category,
                authorId: response.data.author.id,
                availableCopies: response.data.availableCopies,
            });
        } catch (error) {
            console.error('Error fetching book', error)
        }
    }

    useEffect(() => {
        fetchBook()
        fetchAuthors()
    }, [])

    const handleChange = (e) => {
        const { name, value, type } = e.target;
        if (type === 'select-one') {
            setFormData({ ...formData, [name]: value });
        } else {
            setFormData({ ...formData, [name]: type === 'number' ? parseInt(value) : value });
        }
    };

    const onClose = () => {
        navigate('/books')
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        const request = {
            "name": formData.name,
            "category": formData.category,
            "authorId": formData.authorId,
            "availableCopies": formData.availableCopies
        }
        try {
            if (!id) {
                await axios.post('/api/books', request);
            } else {
                await axios.put(`/api/books/${id}`, request);
            }
            onClose()
        } catch (error) {
            console.error('Error editing book:', error);
        }
    };
    return (
        <div>
            <h2>{!id ? "Add Book" : "Edit Book"}</h2>
            <form onSubmit={handleSubmit}>
                <label>Name:</label>
                <input type="text" name="name" value={formData.name} onChange={handleChange}/>

                <label>Category:</label>
                <select onChange={handleChange} value={formData.category}>
                    {Object.entries(Categories).map(([key, value]) => (
                        <option key={key} value={key}>
                            {value}
                        </option>
                    ))}
                </select>

                <label>Author</label>
                <select onChange={handleChange} value={formData.authorId}>
                    {authors.map((author) => (
                        <option key={author.id} value={author.id}>
                            {author.name + " " + author.surname}
                        </option>
                    ))}
                </select>

                <label>Available Copies:</label>
                <input type="number" name="availableCopies" value={formData.availableCopies} onChange={handleChange}/>

                <button type="submit">Save</button>
                <button type="button" onClick={onClose}>Cancel</button>
            </form>
        </div>
    );
}

export default BookEdit