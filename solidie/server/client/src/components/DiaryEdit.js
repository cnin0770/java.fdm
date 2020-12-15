import React, { Component } from 'react';
import { instanceOf } from 'prop-types';
import { Link, withRouter } from 'react-router-dom';
import { withCookies, Cookies } from 'react-cookie';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';

import AppNavbar from '../AppNavbar';

class DiaryEdit extends Component {
  static propTypes = {
    cookies: instanceOf(Cookies).isRequired
  };

  emptyItem = {
    date: '',
    weight: '',
    focus: ''
  };

  constructor(props) {
    super(props);
    const {cookies} = props;
    this.state = {
      item: this.emptyItem,
      csrfToken: cookies.get('XSRF-TOKEN')
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async componentDidMount() {
    if (this.props.match.params.id !== 'new') {
      try {
        const foundItem = await (await fetch(`/api/diaries/${this.props.match.params.id}`, { credentials: 'include' })).json();
        this.setState({ item: foundItem });
      } catch (error) {
        this.props.history.push('/');
      }
    }
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let item = {...this.state.item};
    item[name] = value;
    this.setState({item});
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {item, csrfToken} = this.state;

    await fetch('/api/diaries' + (item.id ? '/' + item.id : ''), {
      method: (item.id) ? 'PUT' : 'POST',
      headers: {
        'X-XSRF-TOKEN': csrfToken,
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(item),
      credentials: 'include'
    });
    this.props.history.push('/diaries');
  }

  render() {
    const {item} = this.state;
    const title = <h2>{item.id ? 'Edit Diary' : 'Add Diary'}</h2>;

    return <div>
      <AppNavbar/>
      <Container>
        {title}
        <Form onSubmit={this.handleSubmit}>
          <FormGroup>
            <Label for="date">Date</Label>
            <Input type="text" name="date" id="date" value={item.date || ''}
                   onChange={this.handleChange} autoComplete="date"/>
          </FormGroup>
          <FormGroup>
            <Label for="weight">Weight</Label>
            <Input type="number" step="0.01" name="weight" id="weight" value={item.weight || ''}
                   onChange={this.handleChange} />
          </FormGroup>
          <FormGroup>
            <Label for="focus">Focus</Label>
            <Input type="text" name="focus" id="focus" value={item.focus || ''}
                   onChange={this.handleChange} autoComplete="focus"/>
          </FormGroup>
          <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
            <Button color="secondary" tag={Link} to="/diaries">Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  }
}

export default withCookies(withRouter(DiaryEdit));