import {React, useEffect, useState} from "react";
import {useParams} from "react-router-dom"
import {MatchDetailCard} from "../components/MatchDetailCard";
import {MatchSmallCard} from "../components/MatchSmallCard";

import "./TeamPage.scss"

export const TeamPage = () => {

    const [team, setTeam] = useState({matches: []});
    const { teamName } = useParams();

    useEffect(
        () => {
            const fetchMatches = async () => {
                let url = 'http://localhost:8181/teams/' + encodeURIComponent(teamName);
                const response = await fetch(url);
                const data = await response.json();
                setTeam(data);
            }
            fetchMatches();
        }, [teamName]
    )

    if (!team || !team.teamName) {
        return <h1>Team not found</h1>
    }

    return (
        <div className="TeamPage">
            <div>

            </div><h1>{team.teamName}</h1>
            <MatchDetailCard teamName={team.teamName} match={team.matches[0]}/>
            {team.matches.slice(1).map(match => <MatchSmallCard teamName={team.teamName} match={match}/>)}
        </div>
    );
}