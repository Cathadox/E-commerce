import React, {useState, useEffect} from 'react';
import {redirect, useNavigate, useParams} from 'react-router-dom';
import axios from "axios";

const AuthorEdit = () => {
    const navigate = useNavigate()

    const [formData, setFormData] = useState({
        name: '',
        surname: '',
        country: 1,
    });

    const [countries, setCountries] = useState([])

    const fetchCountries = async () => {
        try {
            const response = await axios.get('/api/countries')
            setCountries(response.data)
        } catch (error) {
            console.error('Error fetching countries', error)
        }
    }

    useEffect(() => {
        fetchCountries()
    }, [])

    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormData({...formData, [name]: value});
    };

    const onClose = () => {
        navigate('/books')
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        const request = {
            "name": formData.name,
            "surname": formData.surname,
            "country": formData.country
        }
        try {
            await axios.post('/api/authors', request);
            onClose()
        } catch (error) {
            console.error('Error adding author:', error);
        }
    };
    return (
        <div>
            <h2>Add Author</h2>
            <form onSubmit={handleSubmit}>
                <label>Name:</label>
                <input type="text" name="name" value={formData.name} onChange={handleChange}/>

                <label>Surname</label>
                <input type="text" name="surname" value={formData.surname} onChange={handleChange}/>

                <label>Country:</label>
                <select onChange={handleChange} value={formData.country}>
                    {countries.map((country) => (
                        <option key={country.id} value={country.id}>
                            {country.name + ", " + country.continent}
                        </option>
                    ))}
                </select>

                <button type="submit">Save</button>
                <button type="button" onClick={onClose}>Cancel</button>
            </form>
        </div>
    );
}

export default AuthorEdit