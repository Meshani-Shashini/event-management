import { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import Input from '../components/Input';
import Button from '../components/Button';
import '../styles/EventUpdatePage.css';

function EventUpdatePage() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [eventData, setEventData] = useState({
    name: '',
    description: '',
    date: '',
    location: '',
    capacity: '',
  });

  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null); // ✅ for notification
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    axios
      .get(`/api/events/${id}`)
      .then((response) => {
        setEventData(response.data);
        setLoading(false);
      })
      .catch(() => {
        setError('Failed to load event data.');
        setLoading(false);
      });
  }, [id]);

  const handleChange = (e) => {
    setEventData({
      ...eventData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = () => {
    if (!eventData.name || !eventData.date) {
      setError('Name and Date are required.');
      return;
    }

    axios
      .put(`/api/events/${id}`, eventData)
      .then(() => {
        setSuccess('Event updated successfully!');
        setTimeout(() => {
          navigate(`/events/${id}`); // 🔄 redirect to updated event details
        }, 2000); // wait 2 seconds before navigating
      })
      .catch(() => {
        setError('Failed to update event.');
      });
  };

  if (loading) return <div>Loading...</div>;

  return (
    <div className="container">
      <h1>Update Event</h1>

      {error && <div style={{ color: 'red' }}>{error}</div>}
      {success && <div style={{ color: 'green' }}>{success}</div>}

      <Input
        type="text"
        name="name"
        value={eventData.name}
        onChange={handleChange}
        placeholder="Event Name"
        required
      />
      <Input
        type="text"
        name="description"
        value={eventData.description}
        onChange={handleChange}
        placeholder="Description"
      />
      <Input
        type="date"
        name="date"
        value={eventData.date}
        onChange={handleChange}
        placeholder="Date"
        required
      />
      <Input
        type="text"
        name="location"
        value={eventData.location}
        onChange={handleChange}
        placeholder="Location"
      />
      <Input
        type="number"
        name="capacity"
        value={eventData.capacity}
        onChange={handleChange}
        placeholder="Capacity"
      />

      <Button onClick={handleSubmit}>Save Changes</Button>
    </div>
  );
}

export default EventUpdatePage;
