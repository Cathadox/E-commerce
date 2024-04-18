import React, {useState, useEffect} from 'react';
import axios from 'axios';

const BookList = () => {
    const [books, setBooks] = useState([]);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);

    useEffect(() => {
        fetchBooks(0);
    }, []);

    const fetchBooks = async (currPage) => {
        try {
            const response = await axios.get(`/api/books?page=${currPage}&size=5&sort=name,asc`);
            setBooks(response.data.content);
            setTotalPages(response.data.totalPages);
        } catch (error) {
            console.error('Error fetching books:', error);
        }
    };

    const handleDelete = async (id) => {
        try {
            const response = await axios.delete(`/api/books/${id}`);
            fetchBooks(page)
        } catch (error) {
            console.error('Error fetching books:', error);
        }
    };

    const handleMarkAsTaken = async (id) => {
        try {
            const response = await axios.put(`/api/books/rent/${id}`);
            fetchBooks(page)
        } catch (error) {
            console.error('Error fetching books:', error);
        }
    };

    const handleNext = () => {
        setPage(page + 1);
        fetchBooks(page + 1);
    }

    const handlePrev = () => {
        setPage(page - 1);
        fetchBooks(page - 1);
    }

    return (
        <div>
            <h1>Book List</h1>
            <a href={"/books/0"}>Add</a>
            <ul>
                {books.map((book) => (
                    <li key={book.id}>
                        {book.name} - {book.category} - {book.author.name} - {book.availableCopies}
                        <a href={"/books/" + book.id}>Edit</a>
                        <button onClick={() => handleDelete(book.id)}>Delete</button>
                        <button onClick={() => handleMarkAsTaken(book.id)}>Mark As Taken</button>
                    </li>
                ))}
            </ul>
            <button disabled={page===0} onClick={() => handlePrev()}>Prev</button>
            <button disabled={page===totalPages-1} onClick={() => handleNext()}>Next</button>
        </div>
    );
};

export default BookList;