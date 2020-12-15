import React, { Component } from 'react';
import { instanceOf } from 'prop-types';
import { Link, withRouter } from 'react-router-dom';
import { withCookies, Cookies } from 'react-cookie';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';

import AppNavbar from '../AppNavbar';

class ExerciseVisual extends Component {
    static propTypes = {
        cookies: instanceOf(Cookies).isRequired
    };

    constructor(props) {
        super(props);
        const {cookies} = props;
        this.state = { result: [], csrfToken: cookies.get('XSRF-TOKEN'), isLoading: true };
    }

    componentDidMount() {
        this.setState({ isLoading: true });

        fetch('api/exercises', {credentials: 'include'})
            .then(response => response.json())
            .then(data => this.setState({ result: data, isLoading: false }))
            .catch(() => this.props.history.push('/'));
    }

    render() {
        const {result, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading... </p>;
        }

        return (
            <div>
                <AppNavbar/>
            </div>
        )
    }
}

export default withCookies(withRouter(ExerciseVisual));