import React, { Component } from 'react';
import { instanceOf } from 'prop-types';
import { Link, withRouter } from 'react-router-dom';
import { withCookies, Cookies } from 'react-cookie';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';

import AppNavbar from '../AppNavbar';

class DiaryList extends Component {
    static propTypes = {
        cookies: instanceOf(Cookies).isRequired
    };

    constructor(props) {
        super(props);
        const { cookies } = props;
        this.state = { result: [], csrfToken: cookies.get('XSRF-TOKEN'), isLoading: true };
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        this.setState({ isLoading: true });

        fetch('api/diaries', { credentials: 'include' })
            .then(response => response.json())
            .then(data => this.setState({ result: data, isLoading: false }))
            .catch(() => this.props.history.push('/'));
    }

    async remove(id) {
        await fetch(`/api/diaries/${id}`, {
            method: 'DELETE',
            headers: {
                'X-XSRF-TOKEN': this.state.csrfToken,
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            credentials: 'include'
        }).then(() => {
            let updatedResult = [...this.state.result].filter(i => i.id !== id);
            this.setState({ result: updatedResult });
        })
    }

    render() {
        const {result, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading... </p>;
        }

        const itemList = result.map(item => {
            return <tr key={item.id}>
                <td style={{ whiteSpace: 'nowrap' }}>{ item.id }</td>
                <td style={{ whiteSpace: 'nowrap' }}>{ item.date }</td>
                <td style={{ whiteSpace: 'nowrap' }}>{ item.focus }</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="warning" tag={ Link } to={ "/diaries/" + item.id }>
                            Edit</Button>
                        <Button size="sm" color="secondary" onClick={
                            () => this.remove(item.id)
                        }>
                            Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fliud>
                    <div className="float-right">
                        <Button color="light" tag={ Link } to="/diaries/new">Add Diary</Button>
                    </div>
                    <h3>My Diary</h3>
                    <Table className="mt-4">
                        <thead>
                            <tr>
                                <th width="20%">ID</th>
                                <th width="20%">Date</th>
                                <th width="20%">Focus</th>
                                <th width="10%">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            { itemList }
                        </tbody>
                    </Table>
                </Container>
            </div>
        )
    }
}

export default withCookies(withRouter(DiaryList));