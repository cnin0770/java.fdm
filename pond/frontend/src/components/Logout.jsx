import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';


class Logout extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: true
        }
    }

    componentDidMount() {
        sessionStorage.setItem("token", null);
        this.setState({
            isLoading: false
        });
    }

    render() {
        if (this.state.isLoading) {
            return (
                <div>Loading...</div>
            );
        }
        else {
            return (
                <Redirect to={{
                    pathname: '/'
                }}
                />
            );
        }
    }
}

export default Logout;