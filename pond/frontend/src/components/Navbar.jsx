import React, { Component } from 'react';
import { Navbar as BsNavbar, NavbarBrand, NavLink } from 'reactstrap';
import { Link } from 'react-router-dom';


class Navbar extends Component {
    constructor(props) {
        super(props);
        this.state = {
            activeUser: props.activeUser ? props.activeUser : { firstName: '', userID: -1, role: 'ROLE_ANONYMOUS' },
        }
    }

    render() {
        return (
            <BsNavbar bg="dark" variant="dark" expand="md">
                <NavbarBrand href="/">PondMS</NavbarBrand>
      <NavLink href="/viewTrainees">Trainees</NavLink>
                <NavLink href="/opportunity">Opportunities</NavLink>
                <NavLink href="/addSelfLearning">Self Learning</NavLink>
                <NavLink href="/skills">Skills</NavLink>
                <Link to={{
                    pathname: '/logout',
                    state: {
                        activeUser: this.state.activeUser
                    }
                }}>Logout</Link>
            </BsNavbar>
        )
    }
}

export default Navbar;