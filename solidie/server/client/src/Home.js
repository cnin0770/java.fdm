import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { withCookies } from 'react-cookie';
import { Button, Container } from 'reactstrap';

import './App.css';
import AppNavbar from './AppNavbar';

class Home extends Component {
    state = {
        isLoading: true,
        isAuthenticated: false,
        user: undefined
    }

    constructor(props) {
        super(props);
        const { cookies } = props;
        this.state.csrfToken = cookies.get('XSRF-TOEKN');
        this.login = this.login.bind(this);
        this.logout = this.logout.bind(this);
    }

    async componentDidMount() {
        const response = await fetch('/api/user', {credentials: 'include'});
        const body = await response.text();
        if (body === '') {
            this.setState(({ isAuthenticated: false }));
        } else {
            this.setState({ isAuthenticated: true, user: JSON.parse(body) });
        }
    }

    login() {
        let port = (window.location.port ? ':' + window.location.port : '');
        if (port === ':3000') {
            port = ':14350';
        }
        window.location.href = '//' + window.location.hostname + port + '/private';
    }

    logout() {
        fetch('/api/logout', {
            method: 'POST', 
            credentials: 'include',
            headers: {'X-XSRF-TOKEN': this.state.csrfToken}
        }).then(res => res.json())
          .then(response => {
            window.location.href = response.logoutUrl + "?id_token_hint=" +
              response.idToken + "&post_logout_redirect_uri=" + window.location.origin;
          });
      }

    render() {
        const message = this.state.user 
            ? <h2>Welcome, { this.state.user.name }.</h2>
            : <p>Please log in first.</p>;
        
        const button = this.state.isAuthenticated 
            ? <div>
                <Button color="link">
                    <Link to="/exercises" className="link">Manage Exercises</Link>
                </Button>
                <Button color="link">
                    <Link to="/diaries" className="link">Manage Diary</Link>
                </Button>

                <Button color="link">
                    <Link to="/visual" className="link"></Link>
                </Button>
                <hr />

                <Button color="primary" onClick={ this.logout }>
                    Logout
                </Button>
            </div>
            : <Button color="primary" onClick={ this.login }>Login</Button>;
        
        return (
            <div>
                <AppNavbar />
                <main role="main">
                    <div class="jumbotron">
                        <Container fliud>
                            { message }
                            { button }
                        </Container>
                </div>
                </main>
            </div>
        );
    }
}

export default withCookies(Home);