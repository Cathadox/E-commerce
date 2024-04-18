import logo from './logo.svg';
import './App.css';
import {BrowserRouter, createBrowserRouter, Outlet, Route, Routes} from 'react-router-dom';
import BookList from "./BookList";
import NavigationHeader from "./NavigationHeader";
import BookEdit from "./BookEdit";
import CategoryList from "./CategoryList";
import {RouterProvider} from "react-router";


let router = createBrowserRouter([
    {
        path: "/",
        element: <Root />,
        children: [
            {
                path: "/",
                element: <BookList/>,
            },
            {
                path: "/books",
                element: <BookList/>,
            },
            {
                path: "/books/:id",
                element: <BookEdit/>,
            },
            {
                path: "/categories",
                element: <CategoryList/>,
            }
        ],
    },
]);

function Root() {
    return (
        <div>
            <NavigationHeader/>
            <Outlet/>
        </div>
    )
}

function App(){
    return <RouterProvider router={router} fallbackElement={<p>Loading...</p>} />;
}

export default App;
