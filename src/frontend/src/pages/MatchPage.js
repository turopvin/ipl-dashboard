import {React, useEffect, useState} from "react";
import {MatchDetailCard} from "../components/MatchDetailCard";
import {useParams} from "react-router-dom";

export const MatchPage = () => {
    const [matches, setMatches] = useState([]);
    const {teamName, year} = useParams();

    useEffect(
        () => {
            const fetchMatches = async () => {
                let url = 'http://localhost:8181/teams/' + encodeURIComponent(teamName) + "/matches?year=" + year;
                const response = await fetch(url);
                const data = await response.json();
                setMatches(data);
            }
            fetchMatches();
        }, []
    )
    return (
        <div className="MatchPage">
            <h1>Match page</h1>
            {
                matches.map(match => <MatchDetailCard teamName={match.teamName} match={match}/>)
            }
        </div>
    );
}